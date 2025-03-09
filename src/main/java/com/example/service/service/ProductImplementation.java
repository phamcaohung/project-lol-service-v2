package com.example.service.service;

import com.example.service.dto.ProductDTO;
import com.example.service.exception.ProductException;
import com.example.service.mapper.ProductMapper;
import com.example.service.model.*;
import com.example.service.repository.*;
import com.example.service.request.*;
import com.example.service.service.implementation.FileUploadService;
import com.example.service.service.implementation.ProductService;
import com.example.service.service.implementation.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductImplementation implements ProductService {

    private ProductRepository productRepository;
    private ChampionRepository championRepository;
    private SkinRepository skinRepository;
    private ColorRepository colorRepository;
    private UserService userService;
    private FileUploadService fileUploadService;
    private CategoryRepository categoryRepository;

    public ProductImplementation(ProductRepository productRepository, ColorRepository colorRepository,
                                 UserService userService, CategoryRepository categoryRepository,
                                 ChampionRepository championRepository, SkinRepository skinRepository,
                                 FileUploadService fileUploadService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.championRepository = championRepository;
        this.skinRepository = skinRepository;
        this.colorRepository = colorRepository;
        this.fileUploadService = fileUploadService;
    }
    @Override
    @Transactional
    public Product createProduct(CreateProductRequest req, MultipartFile imageFile) throws IOException, SQLException {
        Category category = categoryRepository.findByName(req.getCategory());

        if (category == null) {
            Category level = new Category();
            level.setName(req.getCategory());

            category = categoryRepository.save(level);
        }

        Image image = fileUploadService.storeFileUploadIntoFileSystem(imageFile);

        Product product = new Product();
        product.setImageUpload(image);
        product.setTitle(req.getTitle());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice((int) (req.getPrice() - (req.getPrice() * ((float) req.getDiscountPercent()) /100 )));
        product.setDiscountPercent(req.getDiscountPercent());
        product.setPrice(req.getPrice());
        product.setReleaseDate(req.getReleaseDate());
        product.setCanBeLooted(req.getCanBeLooted());
        product.setInStore(req.getInStore());
        product.setTrailerLink(req.getTrailerLink());

        int quantity = 0;
//        List<Color> colors = new ArrayList<>();
//        for (int i = 0; i < colorRequest.length; i++) {
//            quantity += colorRequest[i].getQuantity();
//            colors.add(createImageFileColor(colorRequest[i], colorImage[i]));
//        }

        for(Color color: req.getColor()) {
            quantity += color.getQuantity();
        }
        product.setColor(req.getColor());

        product.setQuantity(quantity);
        product.setCategory(category);
        product.setCreateAt(LocalDateTime.now());
        productIsSkinOrChampion(req.getChampion(), req.getSkin(), product);

        System.out.println("Create Product Successfully");

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.delete(product);
        product.getColor().clear();
        if (product.getCategory().getName().equals("skin")) {
            Skin skin = skinRepository.findSkinById(product.getSkin().getId());
            skinRepository.delete(skin);
        } else if (product.getCategory().getName().equals("champion")) {
            Champion champion = championRepository.findChampionById(product.getChampion().getId());
            championRepository.delete(champion);
            product.getChampion().getSkill().clear();
        }

        return "Product Deleted Successfully";
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, UpdateProductRequest req, MultipartFile imageFile) throws ProductException, IOException {
        Product product = findProductById(productId);

        if(product.getImageUpload() == null) {
            Image image = fileUploadService.storeFileUploadIntoFileSystem(imageFile);
            product.setImageUpload(image);
        }
        else if(!product.getImageUpload().getName().equals(imageFile.getOriginalFilename())) {
            Image image = fileUploadService.storeFileUploadIntoFileSystem(imageFile);
            product.setImageUpload(image);
        }

        product.setTitle(req.getTitle());
        product.setSkin(req.getSkin());
        product.setChampion(req.getChampion());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice((int) (req.getPrice() - (req.getPrice() * ((float) req.getDiscountPercent()) /100 )));
        product.setDiscountPercent(req.getDiscountPercent());
        product.setPrice(req.getPrice());
        product.setReleaseDate(req.getReleaseDate());
        product.setCanBeLooted(req.getCanBeLooted());
        product.setInStore(req.getInStore());
        product.setTrailerLink(req.getTrailerLink());
        updateColorList(req.getColor(), product);
        int quantity = product.getColor().stream().mapToInt(Color::getQuantity).sum();
        product.setQuantity(quantity);

        System.out.println("Update Product Successfully");

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            System.out.println("Get Product By Id successfully");
            return opt.get();
        }
        throw new ProductException("Product not found with id - " + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<ProductDTO> getAllProduct(String category, List<String> tier, Integer minPrice, Integer maxPrice,
                                          Integer minDiscount, String sort, String name, String series,
                                          String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageble = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice,
                                                                    minDiscount, sort, name, series);

        if (!tier.isEmpty()) {
            products = products.stream().filter(p -> tier.stream().anyMatch(c -> c.equalsIgnoreCase(p.getSkin().getTier())))
                    .collect(Collectors.toList());
        }
        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            }
            else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageble.getOffset();
        int endIndex = Math.min(startIndex + pageble.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        List<ProductDTO> productDTOS = ProductMapper.INSTANCE.mapList(pageContent);

        System.out.println("Get Product successfully");

        return new PageImpl<>(productDTOS, pageble, products.size());
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductBySeriesAndIdNot(String series, Long id) throws ProductException{
        return productRepository.findProductBySkin_SeriesAndIdNot(series, id);
    }

    @Override
    public List<Product> findProductBySeries(String series) throws ProductException {
        return productRepository.findProductBySkin_Series(series);
    }

    @Override
    public List<String> findAllSeriesName(String series) throws ProductException {
//        List<Product> products = productRepository.findProductByCategoryName(category);
//        return products.stream()
//                .map(Product::)
//                .distinct()
//                .sorted()
//                .collect(Collectors.toList());
        List<Skin> skins = skinRepository.findSkinBySeries(series);
        return skins.stream()
                .map(Skin::getSeries)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findNewSkinProduct(String category) throws ProductException {
        return productRepository.findTop12ProductByCategoryNameOrderByReleaseDateDesc(category);
    }

    @Override
    public Product updateImageFileProduct(Long productId, MultipartFile file) throws ProductException, IOException {
        Product product = findProductById(productId);
        Image image = fileUploadService.storeFileUploadIntoFileSystem(file);
        product.setImageUpload(image);

        System.out.println("Update Image File Product Successfully");
        return productRepository.save(product);
    }

    public void productIsSkinOrChampion(Champion requestChampion, Skin requestSkin, Product product) {
        if (requestSkin != null) {
            Skin skin = new Skin();
            skin.setTier(requestSkin.getTier());
            skin.setImageTier(requestSkin.getImageTier());
            skin.setSeries(requestSkin.getSeries());
            skinRepository.save(skin);
            product.setSkin(skin);
        }

        if (requestChampion.getDifficulty() != null) {
            Champion champion = new Champion();
            champion.setRole(requestChampion.getRole());
            champion.setDifficulty(requestChampion.getDifficulty());
            for (Skill e :requestChampion.getSkill())
                e.setChampion(champion);
            champion.setSkill(requestChampion.getSkill());
            championRepository.save(champion);
            product.setChampion(champion);
        }
    }

    public void updateColorList(List<Color> colorUpdate, Product product) {
        Map<Long, Color> colorMap = product.getColor()
                                    .stream()
                                    .collect(Collectors.toMap(Color :: getId, Function.identity()));
        List<Color> toSave = new ArrayList<>();
        for(Color e : colorUpdate) {
            if(colorMap.containsKey(e.getId())) {
                Color oldColor = colorMap.get(e.getId());
                oldColor.setImage(e.getImage());
                oldColor.setName(e.getName());
                oldColor.setColor(e.getColor());
                oldColor.setQuantity(e.getQuantity());

                toSave.add(oldColor);
            }
            else {
                Color newColor = new Color();
                newColor.setImage(e.getImage());
                newColor.setProduct(product);
                newColor.setName(e.getName());
                newColor.setColor(e.getColor());
                newColor.setQuantity(e.getQuantity());

                product.getColor().add(newColor);
                toSave.add(newColor);
            }
        }
        colorRepository.saveAll(toSave);
    }
}

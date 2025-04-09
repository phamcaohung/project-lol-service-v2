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
    private SkillRepository skillRepository;
    private UserService userService;
    private FileUploadService fileUploadService;
    private CategoryRepository categoryRepository;
    private ChibiRepository chibiRepository;

    public ProductImplementation(ProductRepository productRepository, ColorRepository colorRepository,
                                 UserService userService, CategoryRepository categoryRepository,
                                 ChampionRepository championRepository, SkinRepository skinRepository,
                                 FileUploadService fileUploadService, SkillRepository skillRepository,
                                 ChibiRepository chibiRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.championRepository = championRepository;
        this.skinRepository = skinRepository;
        this.colorRepository = colorRepository;
        this.fileUploadService = fileUploadService;
        this.skillRepository = skillRepository;
        this.chibiRepository = chibiRepository;
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

        Image image = fileUploadService.storeImageUploadIntoFileSystem(imageFile, category);

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
        product.setColor(req.getColor());
        int quantity = 0;
        for(Color color: req.getColor()) {
            quantity += color.getQuantity();
            color.setProduct(product);
        }
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setCreateAt(LocalDateTime.now());
        switch (req.getCategory()) {
            case "skin" -> productIsSkin(req.getSkin(), product);
            case "champion" -> productIsChampion(req.getChampion(), product);
            case "chibi" -> productIsChibi(req.getChibi(), product);
        }
        System.out.println("Create Product Successfully");

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.delete(product);

        return "Product Deleted Successfully";
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, UpdateProductRequest req, MultipartFile imageFile) throws ProductException, IOException {
        Product product = findProductById(productId);

        if(product.getImageUpload() == null) {
            Image image = fileUploadService.storeImageUploadIntoFileSystem(imageFile, product.getCategory());
            product.setImageUpload(image);
        }
        else if(!product.getImageUpload().getName().equals(imageFile.getOriginalFilename())) {
            Image image = fileUploadService.storeImageUploadIntoFileSystem(imageFile, product.getCategory());
            product.setImageUpload(image);
        }

        product.setTitle(req.getTitle());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice((int) (req.getPrice() - (req.getPrice() * ((float) req.getDiscountPercent()) /100 )));
        product.setDiscountPercent(req.getDiscountPercent());
        product.setPrice(req.getPrice());
        product.setReleaseDate(req.getReleaseDate());
        product.setCanBeLooted(req.getCanBeLooted());
        product.setInStore(req.getInStore());
        product.setTrailerLink(req.getTrailerLink());
        handleColorList(req.getColor(), product);
        int quantity = product.getColor().stream().mapToInt(Color::getQuantity).sum();
        product.setQuantity(quantity);
        switch (req.getCategory()) {
            case "skin" -> productIsSkin(req.getSkin(), product);
            case "champion" -> productIsChampion(req.getChampion(), product);
            case "chibi" -> productIsChibi(req.getChibi(), product);
        }

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
    public Page<ProductDTO> getAllProduct(FilterProductRequest request) {
        Pageable pageble = PageRequest.of(request.getPageNumber(), request.getPageSize());
        List<Product> products = productRepository.filterProducts(request.getCategory(), request.getMinPrice(),
                                                                  request.getMaxPrice(), request.getMinDiscount(),
                                                                  request.getSort(), request.getTitle(),
                                                                  request.getSkin().getSeries(), request.getChibi().getChampion(),
                                                                  request.getChampion().getRegion(), request.getChampion().getRole());
        if (!request.getSkin().getTier().isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getSkin().getTier().equals(request.getSkin().getTier()))
                    .collect(Collectors.toList());
        }

        if (!request.getChibi().getTier().isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getChibi().getTier().equals(request.getChibi().getTier()))
                    .collect(Collectors.toList());
        }

        if (request.getStock() != null) {
            if (request.getStock().equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            }
            else if (request.getStock().equals("out_of_stock")) {
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
    public List<Product> findSkinProductBySeriesAndIdNot(String series, Long id) throws ProductException{
        return productRepository.findProductBySkin_SeriesAndIdNot(series, id);
    }

    @Override
    public List<Product> findChampionProductBySeriesAndIdNot(String region, Long id) throws ProductException{
        return productRepository.findProductByChampion_RegionAndIdNot(region, id);
    }

    @Override
    public List<Product> findChibiProductBySeriesAndIdNot(String champion, Long id) throws ProductException{
        return productRepository.findProductByChibi_ChampionAndIdNot(champion, id);
    }

    @Override
    public List<Product> findProductBySeries(String series) throws ProductException {
        return productRepository.findProductBySkin_Series(series);
    }

    @Override
    public List<String> findAllSeriesName(String category) throws ProductException {
        switch (category) {
            case "skin" -> {
                return skinRepository.findDistinctSeries();
            }
            case "chibi" -> {
                return chibiRepository.findDistinctChampion();
            }
        }
        return null;
    }

    @Override
    public List<Product> findNewSkinProduct(String category) throws ProductException {
        return productRepository.findTop12ProductByCategoryNameOrderByReleaseDateDesc(category);
    }

    public void handleColorList(List<Color> colorUpdate, Product product) {
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

    private void productIsChibi(Chibi chibiRequest, Product product) {
        Chibi chibi = product.getChibi();
        if (chibi == null) {
            chibi = new Chibi();
            product.setChibi(chibi);
        }
        chibi.setTier(chibiRequest.getTier());
        chibi.setImageTier(chibiRequest.getImageTier());
        chibi.setChampion(chibiRequest.getChampion());
        chibiRepository.save(chibi);
    }

    private void productIsChampion(Champion championRequest, Product product) {
        Champion champion = product.getChampion();
        if (champion == null) {
            champion = new Champion();
            product.setChampion(champion);
        }
        champion.setRole(championRequest.getRole());
        champion.setImageRole(championRequest.getImageRole());
        champion.setRegion(championRequest.getRegion());
        champion.setImageRegion(championRequest.getImageRegion());
        champion.setDifficulty(championRequest.getDifficulty());
        List<Skill> skills = championRequest.getSkill();
        if (skills != null) {
            for (Skill e : championRequest.getSkill()) {
                e.setChampion(champion);
                skillRepository.save(e);
            }
            champion.setSkill(skills);
        }
        championRepository.save(champion);
    }

    private void productIsSkin(Skin skinRequest, Product product) {
        Skin skin = product.getSkin();
        if (skin == null) {
            skin = new Skin();
            product.setSkin(skin);
        }
        skin.setTier(skinRequest.getTier());
        skin.setImageTier(skinRequest.getImageTier());
        skin.setSeries(skinRequest.getSeries());
        skinRepository.save(skin);
    }
}

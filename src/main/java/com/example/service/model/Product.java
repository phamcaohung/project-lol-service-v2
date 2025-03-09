package com.example.service.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int price;

    @Column(name = "discounted_price")
    private int discountedPrice;

    @Column(name = "discount_percent")
    private int discountPercent;

    private int quantity;

    private Date releaseDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_upload_id")
    private Image imageUpload;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createAt;

    @Column(name = "trailer_link")
    private String trailerLink;

    @Column(name = "in_store")
    private Boolean inStore;

    @Column(name = "can_be_looted")
    private Boolean canBeLooted;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Color> color = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "champion_id")
    private Champion champion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skin_id")
    private Skin skin;

    public Product() {
    }

    public Product(Long id, String title, int price, int discountedPrice, int discountPercent, int quantity, Date releaseDate, Category category, LocalDateTime createAt, String trailerLink, Boolean inStore, Boolean canBeLooted, String description, List<Color> color, Champion champion, Skin skin, Image imageUpload) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.releaseDate = releaseDate;
        this.category = category;
        this.createAt = createAt;
        this.trailerLink = trailerLink;
        this.inStore = inStore;
        this.canBeLooted = canBeLooted;
        this.description = description;
        this.color = color;
        this.champion = champion;
        this.skin = skin;
        this.imageUpload = imageUpload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public Boolean getInStore() {
        return inStore;
    }

    public void setInStore(Boolean inStore) {
        this.inStore = inStore;
    }

    public Boolean getCanBeLooted() {
        return canBeLooted;
    }

    public void setCanBeLooted(Boolean canBeLooted) {
        this.canBeLooted = canBeLooted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;

    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Image getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(Image imageUpload) {
        this.imageUpload = imageUpload;
    }
}

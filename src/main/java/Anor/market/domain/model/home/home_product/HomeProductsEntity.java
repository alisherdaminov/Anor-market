package Anor.market.domain.model.home.home_product;

import Anor.market.domain.model.catalog.product.comments.CommentsEntity;
import Anor.market.domain.model.catalog.product.images.ProductImageEntity;
import Anor.market.domain.model.home.HomeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "home_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    @Column(name = "seller_name")
    private String sellerName;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "delivery_title")
    private String deliveryTitle;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_color")
    private String productColor;
    @Column(name = "price")
    private int price;
    @Column(name = "discount_with_card_percent")
    private int discountWithCardPercent;
    @Column(name = "discount_price_with_card")
    private int discountPriceWithCard;
    @Column(name = "discount_without_card_percent")
    private int discountWithoutCardPercent;
    @Column(name = "discount_price_without_card")
    private int discountPriceWithoutCard;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    //Home
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private HomeEntity homeEntity;

    //Home Product Images
    @OneToMany(mappedBy = "homeProductsEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImageEntity> productImages = new ArrayList<>();

    //Comments
    @OneToMany(mappedBy = "homeProductsEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentsEntity> commentsEntityList = new ArrayList<>();


}

package Anor.market.domain.model.entity.top_products;

import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import Anor.market.domain.model.entity.top_products.images.TopProductImageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "top_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID topProductsId;
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
    @Column(name = "discount__price_with_card")
    private int discountPriceWithCard;
    @Column(name = "discount_without_card_percent")
    private int discountWithoutCardPercent;
    @Column(name = "discount_price_without_card")
    private int discountPriceWithoutCard;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "is_top_product_start_date")
    private LocalDate isTopProductStartDate;
    @Column(name = "is_top_product_end_date")
    private LocalDate isTopProductEndDate;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topProducts")
    private ProductEntity productEntityTop;

    //Images
    @OneToMany(mappedBy = "topProductsEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImageEntity> images = new ArrayList<>();

    //Top Product Images
    @OneToMany(mappedBy = "topProductsImageEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TopProductImageEntity> topProductsImageList = new ArrayList<>();
}

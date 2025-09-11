package Anor.market.domain.model.catalog.product.images;

import Anor.market.domain.model.catalog.product.products.ProductEntity;
import Anor.market.domain.model.home.home_product.HomeProductsEntity;
import Anor.market.domain.model.top_products.TopProductsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageEntity {

    @Id
    private UUID imageId;
    @Column(name = "origen_name")
    private String origenName;
    @Column(name = "extension")
    private String extension;
    @Column(name = "path")
    private String path;
    @Column(name = "size")
    private Long size;
    @Column(name = "url")
    private String url;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    //Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntityImage;

    //Top Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_products_id")
    private TopProductsEntity topProductsEntity;

    //Home
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_products_image_id")
    private HomeProductsEntity homeProductsEntity;
}

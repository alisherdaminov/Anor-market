package Anor.market.domain.model.entity.top_products.images;

import Anor.market.domain.model.entity.top_products.TopProductsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "top_products_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopProductImageEntity {

    @Id
    private String topProductImageId;
    @Column(name = "origenName")
    private String origenName;
    @Column(name = "extension")
    private String extension;
    @Column(name = "path")
    private String path;
    @Column(name = "size")
    private Long sizes;
    @Column(name = "url")
    private String url;
    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    //Top Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_products_image_id")
    private TopProductsEntity topProductsImageEntity;
}

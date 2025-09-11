package Anor.market.domain.model.top_products.images;

import Anor.market.domain.model.top_products.TopProductsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "top_products_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopProductImageEntity {

    @Id
    private UUID topProductImageId;
    @Column(name = "origen_name")
    private String origenName;
    @Column(name = "extension")
    private String extension;
    @Column(name = "path")
    private String path;
    @Column(name = "size")
    private Long sizes;
    @Column(name = "url")
    private String url;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    //Top Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_products_id")
    private TopProductsEntity topProductsImageEntity;
}

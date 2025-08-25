package Anor.market.domain.model.entity.catalog.product.images;

import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String imageId;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "content_type", nullable = false)
    private String contentType;
    @Column(name = "size_bytes", nullable = false)
    private long sizeBytes;
    @Column(name = "sort_order", nullable = false)
    private int sortOrder;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    //Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntityImage;
}

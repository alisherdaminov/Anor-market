package Anor.market.domain.model.entity.catalog.favorite;

import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String favoriteId;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Products
    @Column(name = "products_favorite_id")
    private String productId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_favorite_id", insertable = false, updatable = false)
    private ProductEntity productEntityFavorite;
}

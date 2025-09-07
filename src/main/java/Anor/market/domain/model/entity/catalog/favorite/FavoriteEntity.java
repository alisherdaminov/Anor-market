package Anor.market.domain.model.entity.catalog.favorite;

import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID favoriteId;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Products
    @Column(name = "products_favorite_id")
    private UUID productId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_favorite_id", insertable = false, updatable = false)
    private ProductEntity productEntityFavorite;
}

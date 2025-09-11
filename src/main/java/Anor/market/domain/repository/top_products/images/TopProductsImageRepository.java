package Anor.market.domain.repository.top_products.images;

import Anor.market.domain.model.top_products.images.TopProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopProductsImageRepository extends JpaRepository<TopProductImageEntity, String> {
}

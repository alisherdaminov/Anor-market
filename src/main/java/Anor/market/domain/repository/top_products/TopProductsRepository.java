package Anor.market.domain.repository.top_products;

import Anor.market.domain.model.top_products.TopProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopProductsRepository extends JpaRepository<TopProductsEntity, String> {
}

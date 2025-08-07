package Anor.market.domain.repository.catalog;

import Anor.market.domain.model.entity.catalog.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}

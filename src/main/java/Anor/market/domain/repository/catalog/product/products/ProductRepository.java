package Anor.market.domain.repository.catalog.product.products;

import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    Page<ProductEntity> findByOrderByLocalDateTimeDesc(Pageable pageable);

}

package Anor.market.domain.repository.catalog.product.products;

import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Page<ProductEntity> findByOrderByLocalDateTimeDesc(Pageable pageable);

    default Optional<ProductEntity> findByStringId(String id) {
        return findById(UUID.fromString(id));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductEntity c WHERE c.productId = :productId")
    void deleteByProductId(@Param("productId") String productId);
}

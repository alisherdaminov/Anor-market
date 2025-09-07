package Anor.market.domain.repository.catalog.product.image;

import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, UUID> {

    default Optional<ProductImageEntity> findByStringId(String id) {
        return findById(UUID.fromString(id));
    }
}

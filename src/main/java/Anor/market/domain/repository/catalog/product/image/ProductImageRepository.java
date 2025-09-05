package Anor.market.domain.repository.catalog.product.image;

import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, String> {

    List<ProductImageEntity> findByImageId(String imageId);

}

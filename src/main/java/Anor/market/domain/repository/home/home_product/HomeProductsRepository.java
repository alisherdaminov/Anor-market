package Anor.market.domain.repository.home.home_product;

import Anor.market.domain.model.home.home_product.HomeProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HomeProductsRepository extends JpaRepository<HomeProductsEntity, UUID> {

    default Optional<HomeProductsEntity> findByStringId(String homeId) {
        return findById(UUID.fromString(homeId));
    }

    List<HomeProductsEntity> findByHomeEntity_HomeId(UUID homeId);

    @Transactional
    @Modifying
    @Query("DELETE FROM HomeProductsEntity h WHERE h.productId =: productId")
    void deleteHomeProductById(@Param("productId") String productId);
}

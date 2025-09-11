package Anor.market.domain.repository.catalog.cart;

import Anor.market.domain.model.catalog.cart.CartEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    @Query("select c from CartEntity c order by c.localDateTime desc")
    List<CartEntity> findAllCarts();

    @Transactional
    @Modifying
    @Query("delete from CartEntity c where c.cartId = ?1")
    void deleteCartsById(String cartsId);

    default Optional<CartEntity> findByStringId(String id){
        return findById(UUID.fromString(id));
    }
}

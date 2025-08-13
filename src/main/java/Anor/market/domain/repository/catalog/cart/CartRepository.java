package Anor.market.domain.repository.catalog.cart;

import Anor.market.domain.model.entity.catalog.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {


}

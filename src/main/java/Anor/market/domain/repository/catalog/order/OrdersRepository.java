package Anor.market.domain.repository.catalog.order;

import Anor.market.domain.model.catalog.order.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID> {

    default Optional<OrdersEntity> findByStringId(String id) {
        return findById(UUID.fromString(id));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM OrdersEntity c WHERE c.ordersId = :ordersId")
    void deleteByOrdersId(@Param("ordersId") String ordersId);
}

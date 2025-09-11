package Anor.market.domain.repository.catalog.order;

import Anor.market.domain.model.catalog.order.CardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardsRepository extends JpaRepository<CardsEntity, UUID> {

    default Optional<CardsEntity> findByStringId(String id) {
        return findById(UUID.fromString(id));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM CardsEntity c WHERE c.cardsId = :cardsId")
    void deleteByCardsId(@Param("cardsId") String cardsId);
}

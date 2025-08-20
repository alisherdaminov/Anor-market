package Anor.market.domain.repository.catalog.order;

import Anor.market.domain.model.entity.catalog.order.CardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<CardsEntity, String> {
}

package Anor.market.domain.repository.catalog.order;

import Anor.market.domain.model.entity.catalog.order.BranchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchesRepository extends JpaRepository<BranchesEntity, String> {
}

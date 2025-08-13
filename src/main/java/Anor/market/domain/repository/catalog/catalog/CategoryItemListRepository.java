package Anor.market.domain.repository.catalog.catalog;

import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryItemListRepository extends JpaRepository<CategoryItemListEntity, String> {
}

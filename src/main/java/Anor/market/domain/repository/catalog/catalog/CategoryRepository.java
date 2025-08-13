package Anor.market.domain.repository.catalog.catalog;

import Anor.market.domain.model.entity.catalog.catalog.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}

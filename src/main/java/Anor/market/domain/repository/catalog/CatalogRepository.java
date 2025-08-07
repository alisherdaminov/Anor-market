package Anor.market.domain.repository.catalog;

import Anor.market.domain.model.entity.catalog.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogEntity, String> {
}

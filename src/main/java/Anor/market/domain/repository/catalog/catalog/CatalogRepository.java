package Anor.market.domain.repository.catalog.catalog;

import Anor.market.domain.model.entity.catalog.catalog.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {

    Page<CatalogEntity> findByOrderByCreatedAtDesc(Pageable pageable);

    default Optional<CatalogEntity> findByStringId(String id) {
        return findById(UUID.fromString(id));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM CatalogEntity c WHERE c.catalogId = :catalogId")
    void deleteByCatalogId(@Param("catalogId") String catalogId);

}

package Anor.market.domain.repository.catalog.catalog;

import Anor.market.domain.model.entity.catalog.catalog.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    default Optional<CategoryEntity> findByStringId(String id){
        return findById(UUID.fromString(id));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM CategoryEntity c WHERE c.categoryId = :categoryId")
    void deleteByCategoryId(@Param("categoryId") String categoryId);
}

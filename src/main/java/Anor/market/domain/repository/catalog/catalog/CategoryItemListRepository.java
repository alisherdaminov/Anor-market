package Anor.market.domain.repository.catalog.catalog;

import Anor.market.domain.model.catalog.catalog.CategoryItemListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryItemListRepository extends JpaRepository<CategoryItemListEntity, UUID> {

    default Optional<CategoryItemListEntity> findByStringId(String id){
        return findById(UUID.fromString(id));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM CategoryItemListEntity c WHERE c.categoryItemListId = :categoryItemListId")
    void deleteByCategoryItemListId(@Param("categoryItemListId") String categoryItemListId);
}

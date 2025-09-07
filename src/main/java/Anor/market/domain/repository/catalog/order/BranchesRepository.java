package Anor.market.domain.repository.catalog.order;

import Anor.market.domain.model.entity.catalog.order.BranchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchesRepository extends JpaRepository<BranchesEntity, UUID> {

    default Optional<BranchesEntity> findByStringId(String id){
        return findById(UUID.fromString(id));
    }


    @Transactional
    @Modifying
    @Query("DELETE FROM BranchesEntity c WHERE c.branchesId = :branchesId")
    void deleteByBranchesId(@Param("branchesId") String branchesId);
}
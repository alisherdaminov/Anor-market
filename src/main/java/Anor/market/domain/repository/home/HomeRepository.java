package Anor.market.domain.repository.home;

import Anor.market.domain.model.home.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, UUID> {

    default Optional<HomeEntity> findByStringId(String homeId) {
        return findById(UUID.fromString(homeId));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM HomeEntity h WHERE h.homeId =: homeId")
    void deleteHomeById(@Param("homeId") String homeId);
}

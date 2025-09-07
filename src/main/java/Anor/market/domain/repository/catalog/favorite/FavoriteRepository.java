package Anor.market.domain.repository.catalog.favorite;

import Anor.market.domain.model.entity.catalog.favorite.FavoriteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, UUID> {

    @Transactional
    @Modifying
    @Query("delete from FavoriteEntity f where f.favoriteId = ?1")
    void deleteFavoriteById(String favoriteId);

    default Optional<FavoriteEntity> findByStringId(String id){
        return findById(UUID.fromString(id));
    }

}

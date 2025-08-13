package Anor.market.domain.repository.catalog.favorite;

import Anor.market.domain.model.entity.catalog.favorite.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, String> {


}

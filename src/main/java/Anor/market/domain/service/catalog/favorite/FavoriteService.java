package Anor.market.domain.service.catalog.favorite;


import Anor.market.application.dto.catalog.favorite.create.FavoriteCreateDTO;
import Anor.market.application.dto.catalog.favorite.dto.FavoriteDTO;

import java.util.List;

public interface FavoriteService {

    FavoriteDTO createFavorite(FavoriteCreateDTO createDTO);

    List<FavoriteDTO> getAllFavorites();

    String deleteFavorite(String favoriteId);
}

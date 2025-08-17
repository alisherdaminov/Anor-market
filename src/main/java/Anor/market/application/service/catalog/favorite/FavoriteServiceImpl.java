package Anor.market.application.service.catalog.favorite;

import Anor.market.application.dto.catalog.favorite.create.FavoriteCreateDTO;
import Anor.market.application.dto.catalog.favorite.dto.FavoriteDTO;
import Anor.market.application.mapper.catalog.favorite.FavoriteMapper;
import Anor.market.domain.model.entity.catalog.favorite.FavoriteEntity;
import Anor.market.domain.repository.catalog.favorite.FavoriteRepository;
import Anor.market.domain.service.catalog.favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * FavoriteServiceImpl implements FavoriteService for making users favorite like or dislike of the markets productions
 * The user can like or dislike, getting all of the products by using like functions
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private FavoriteMapper favoriteMapper;

    /// CREATE A FAVORITE
    @Override
    public FavoriteDTO createFavorite(FavoriteCreateDTO createDTO) {
        FavoriteEntity favoriteEntity = favoriteMapper.toEntity(createDTO);
        favoriteRepository.save(favoriteEntity);
        return favoriteMapper.toDTO(favoriteEntity);
    }

    /// GET ALL FAVORITES
    @Override
    public List<FavoriteDTO> getAllFavorites() {
        return favoriteRepository.findAll()
                .stream().map(favoriteMapper::toDTO)
                .sorted(Comparator.comparing(FavoriteDTO::getLocalDateTime))
                .toList();
    }

    /// DELETE THE FAVORITES BY FAVORITE'S ID
    @Override
    public String deleteFavorite(String favoriteId) {
        favoriteRepository.deleteFavoriteById(favoriteId);
        return "Deleted!";
    }
}

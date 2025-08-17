package Anor.market.application.mapper.catalog.favorite;

import Anor.market.application.dto.catalog.favorite.create.FavoriteCreateDTO;
import Anor.market.application.dto.catalog.favorite.dto.FavoriteDTO;
import Anor.market.application.mapper.catalog.product.ProductMapper;
import Anor.market.domain.model.entity.catalog.favorite.FavoriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FavoriteMapper {

    @Autowired
    private ProductMapper productMapper;

    /// DTO TO ENTITY
    public FavoriteEntity toEntity(FavoriteCreateDTO createDTO) {
        if (createDTO == null) return null;

        return FavoriteEntity.builder()
                .productId(createDTO.getProductId())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public FavoriteDTO toDTO(FavoriteEntity favoriteEntity) {
        if (favoriteEntity == null) return null;

        return FavoriteDTO.builder()
                .favoriteId(favoriteEntity.getFavoriteId())
                .productDTO(productMapper.toProductDTO(favoriteEntity.getProductEntityFavorite()))
                .localDateTime(favoriteEntity.getLocalDateTime())
                .build();
    }
}

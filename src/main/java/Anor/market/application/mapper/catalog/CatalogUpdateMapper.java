package Anor.market.application.mapper.catalog;

import Anor.market.application.dto.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.create.CategoryCreateDTO;
import Anor.market.application.dto.catalog.create.CategoryItemListCreateDTO;
import Anor.market.domain.model.entity.catalog.CatalogEntity;
import Anor.market.domain.model.entity.catalog.CategoryEntity;
import Anor.market.domain.model.entity.catalog.CategoryItemListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class CatalogUpdateMapper {
    @Autowired
    private ProductMapper mapper;


    /// DTO TO ENTITY UPDATE
    public CatalogEntity toUpdateCatalogEntity(String catalogId, CatalogCreateDTO createDTO) {
        return CatalogEntity.builder()
                .catalogId(catalogId)
                .catalogName(createDTO.getCatalogName())
                .categoryEntityList(createDTO.getCategoryCreateList().stream().map(this::toUpdateCategoryEntity).collect(Collectors.toList()))
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY UPDATE
    public CategoryEntity toUpdateCategoryEntity(CategoryCreateDTO createDTO) {
        return CategoryEntity.builder()
                .categoryName(createDTO.getCategoryName())
                .categoryItemListEntityList(createDTO.getCategoryItemListCreateList().stream().map(this::toUpdateCategoryItemListEntity).collect(Collectors.toList()))
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY UPDATE
    public CategoryItemListEntity toUpdateCategoryItemListEntity(CategoryItemListCreateDTO createDTO) {
        return CategoryItemListEntity.builder()
                .categoryItemListName(createDTO.getCategoryItemListName())
                .productEntityList(createDTO.getProductList().stream().map(mapper::toProductEntity).collect(Collectors.toList()))
                .localDateTime(LocalDateTime.now())
                .build();
    }
}

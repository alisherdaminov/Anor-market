package Anor.market.application.mapper.catalog.catalog;

import Anor.market.application.dto.catalog.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.catalog.create.CategoryCreateDTO;
import Anor.market.application.dto.catalog.catalog.create.CategoryItemListCreateDTO;
import Anor.market.application.dto.catalog.catalog.dto.CatalogDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryItemListDTO;
import Anor.market.application.mapper.catalog.product.products.ProductMapper;
import Anor.market.domain.model.catalog.catalog.CatalogEntity;
import Anor.market.domain.model.catalog.catalog.CategoryEntity;
import Anor.market.domain.model.catalog.catalog.CategoryItemListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class CatalogMapper {

    @Autowired
    private ProductMapper mapper;

    /// DTO TO ENTITY
    public CatalogEntity toCatalogEntity(CatalogCreateDTO createDTO) {
        return CatalogEntity.builder()
                .catalogName(createDTO.getCatalogName())
                .categoryEntityList(createDTO.getCategoryCreateList().stream().map(this::toCategoryEntity).collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY
    public CategoryEntity toCategoryEntity(CategoryCreateDTO createDTO) {
        return CategoryEntity.builder()
                .categoryName(createDTO.getCategoryName())
                .categoryItemListEntityList(createDTO.getCategoryItemListCreateList().stream().map(this::toCategoryItemListEntity).collect(Collectors.toList()))
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY
    public CategoryItemListEntity toCategoryItemListEntity(CategoryItemListCreateDTO createDTO) {
        return CategoryItemListEntity.builder()
                .categoryItemListName(createDTO.getCategoryItemListName())
                .productEntityList(createDTO.getProductList().stream().map(mapper::toProductEntity).collect(Collectors.toList()))
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public CatalogDTO toCatalogDTO(CatalogEntity catalogEntity) {
        return CatalogDTO.builder()
                .catalogId(catalogEntity.getCatalogId())
                .catalogName(catalogEntity.getCatalogName())
                .categoryList(catalogEntity.getCategoryEntityList().stream().map(this::toCategoryDTO).collect(Collectors.toList()))
                .localDateTime(catalogEntity.getCreatedAt())
                .build();
    }

    /// ENTITY TO DTO
    public CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .categoryId(categoryEntity.getCategoryId())
                .categoryName(categoryEntity.getCategoryName())
                .categoryItemList(categoryEntity.getCategoryItemListEntityList().stream().map(this::toCategoryItemListDTO).collect(Collectors.toList()))
                .localDateTime(categoryEntity.getLocalDateTime())
                .build();
    }

    /// ENTITY TO DTO
    public CategoryItemListDTO toCategoryItemListDTO(CategoryItemListEntity category) {
        return CategoryItemListDTO.builder()
                .categoryItemListId(category.getCategoryItemListId())
                .categoryItemListName(category.getCategoryItemListName())
                .productList(category.getProductEntityList().stream().map(mapper::toProductDTO).collect(Collectors.toList()))
                .localDateTime(category.getLocalDateTime())
                .build();
    }


}

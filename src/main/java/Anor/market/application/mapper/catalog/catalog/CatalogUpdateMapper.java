package Anor.market.application.mapper.catalog.catalog;

import Anor.market.application.dto.catalog.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.catalog.create.CategoryCreateDTO;
import Anor.market.application.dto.catalog.catalog.create.CategoryItemListCreateDTO;
import Anor.market.application.mapper.catalog.product.products.ProductMapper;
import Anor.market.domain.model.entity.catalog.catalog.CatalogEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class CatalogUpdateMapper {
    @Autowired
    private ProductMapper mapper;


    /// DTO TO ENTITY UPDATE
    public CatalogEntity toUpdateCatalogEntity(String catalogId, String productId, CatalogCreateDTO createDTO) {
        return CatalogEntity.builder()
                .catalogId(catalogId)
                .catalogName(createDTO.getCatalogName())
                .categoryEntityList(createDTO.getCategoryCreateList().stream().map(categoryCreateDTO -> this.toUpdateCategoryEntity(productId, categoryCreateDTO)).collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY UPDATE
    public CategoryEntity toUpdateCategoryEntity(String productId, CategoryCreateDTO createDTO) {
        return CategoryEntity.builder()
                .categoryName(createDTO.getCategoryName())
                .categoryItemListEntityList(createDTO.getCategoryItemListCreateList().stream().map(createDTO1 -> this.toUpdateCategoryItemListEntity(productId, createDTO1)).collect(Collectors.toList()))
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY UPDATE
    public CategoryItemListEntity toUpdateCategoryItemListEntity(String productId, CategoryItemListCreateDTO createDTO) {
        return CategoryItemListEntity.builder()
                .categoryItemListName(createDTO.getCategoryItemListName())
                .productEntityList(createDTO.getProductList().stream().map(productCreateDTO -> mapper.toUpdateProductEntity(productId, productCreateDTO)).collect(Collectors.toList()))
                .localDateTime(LocalDateTime.now())
                .build();
    }
}

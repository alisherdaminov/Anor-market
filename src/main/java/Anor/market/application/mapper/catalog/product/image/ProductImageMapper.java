package Anor.market.application.mapper.catalog.product.image;

import Anor.market.application.dto.catalog.product.images.ProductImageDTO;
import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import Anor.market.domain.model.entity.top_products.TopProductsEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class ProductImageMapper {

    public ProductImageDTO toDTO(ProductImageEntity entity, String baseUrl) {
        if (entity == null) {
            return null;
        }

        return ProductImageDTO.builder()
                .imageId(entity.getImageId())
                .origenName(entity.getOrigenName())
                .extension(entity.getExtension())
                .path(entity.getPath())
                .size(entity.getSize())
                .url(entity.getUrl() != null ? entity.getUrl()
                        : baseUrl + "/" + entity.getPath() + "/" + entity.getImageId() + "." + entity.getExtension())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public List<ProductImageDTO> toDTOList(List<ProductImageEntity> entities, String baseUrl) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(e -> toDTO(e, baseUrl))
                .toList();
    }
    /// ENTITY TO DTO LIST
    public List<ProductImageDTO> productImageDTOList(ProductEntity productEntity) {
        return Optional.ofNullable(productEntity.getImages())
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparing(ProductImageEntity::getCreatedAt).reversed())
                .map(productImageEntity -> new ProductImageDTO(
                        productImageEntity.getImageId(),
                        productImageEntity.getOrigenName(),
                        productImageEntity.getExtension(),
                        productImageEntity.getPath(),
                        productImageEntity.getSize(),
                        productImageEntity.getUrl(),
                        productImageEntity.getCreatedAt()))
                .toList();
    }

    /// ENTITY TO DTO LIST
    public List<ProductImageDTO> topProductsImageList(TopProductsEntity productEntity) {
        return Optional.ofNullable(productEntity.getImages())
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparing(ProductImageEntity::getCreatedAt).reversed())
                .map(productImageEntity -> new ProductImageDTO(
                        productImageEntity.getImageId(),
                        productImageEntity.getOrigenName(),
                        productImageEntity.getExtension(),
                        productImageEntity.getPath(),
                        productImageEntity.getSize(),
                        productImageEntity.getUrl(),
                        productImageEntity.getCreatedAt()))
                .toList();
    }

}

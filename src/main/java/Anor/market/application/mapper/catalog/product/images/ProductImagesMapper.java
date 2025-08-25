package Anor.market.application.mapper.catalog.product.images;

import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.dto.catalog.product.images.ProductImageDTO;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductImagesMapper {


    public ProductDTO toDTO(ProductEntity e) {
        List<ProductImageDTO> productImageDTOList = e.getImages().stream()
                .sorted(Comparator.comparingInt(ProductImageEntity::getSortOrder))
                .map(i -> new ProductImageDTO(
                        i.getImageId(),
                        i.getUrl(),
                        i.getContentType(),
                        i.getSizeBytes(),
                        i.getSortOrder())).collect(Collectors.toList());
        return new ProductDTO(
                e.getProductId(),
                e.getSellerName(),
                e.getProductName(),
                e.getDeliveryTitle(),
                e.getProductDescription(),
                e.getProductColor(),
                e.getPrice(),
                e.getDiscountWithCardPercent(),
                e.getDiscountPriceWithCard(),
                e.getDiscountWithoutCardPercent(),
                e.getDiscountPriceWithoutCard(),
                e.getDeliveryDate(),
                e.getLocalDateTime(),
                productImageDTOList);
    }
}


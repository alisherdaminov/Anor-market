package Anor.market.application.mapper.catalog;

import Anor.market.application.dto.catalog.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.dto.ProductDTO;
import Anor.market.domain.model.entity.catalog.ProductEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {


    /// DTO TO ENTITY
    ProductEntity toProductEntity(ProductCreateDTO createDTO) {
        return ProductEntity.builder()
                .sellerName(createDTO.getSellerName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(createDTO.getPrice())
                .discountWithCard(createDTO.getDiscountWithCard())
                .discountWithoutCard(createDTO.getDiscountWithoutCard())
                .deliveryDate(createDTO.getDeliveryDate())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY UPDATE
    ProductEntity toUpdateProductEntity(String productId, ProductCreateDTO createDTO) {
        return ProductEntity.builder()
                .productId(productId)
                .sellerName(createDTO.getSellerName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(createDTO.getPrice())
                .discountWithCard(createDTO.getDiscountWithCard())
                .discountWithoutCard(createDTO.getDiscountWithoutCard())
                .deliveryDate(createDTO.getDeliveryDate())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    ProductDTO toProductDTO(ProductEntity product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .sellerName(product.getSellerName())
                .deliveryTitle(product.getDeliveryTitle())
                .productDescription(product.getProductDescription())
                .productColor(product.getProductColor())
                .price(product.getPrice())
                .discountWithCard(product.getDiscountWithCard())
                .discountWithoutCard(product.getDiscountWithoutCard())
                .deliveryDate(product.getDeliveryDate())
                .localDateTime(product.getLocalDateTime())
                .build();
    }
}

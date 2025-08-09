package Anor.market.application.mapper.catalog;

import Anor.market.application.dto.catalog.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.dto.ProductDTO;
import Anor.market.domain.model.entity.catalog.ProductEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ProductMapper {


    /// DTO TO ENTITY
    public ProductEntity toProductEntity(ProductCreateDTO createDTO) {
        int productPrice = Integer.parseInt(createDTO.getPrice());
        int discountWithCard = createDTO.getDiscountWithCard();
        int discountWithoutCard = createDTO.getDiscountWithoutCard();
        int resultsOfDiscountWithCard = 0;
        int resultsOfDiscountWithoutCard = 0;
        if (productPrice > 0) {
            resultsOfDiscountWithCard = productPrice % discountWithCard;
            resultsOfDiscountWithoutCard = productPrice % discountWithoutCard;
        }

        LocalDate deliveryDate = LocalDate.parse(createDTO.getDeliveryDate()); // "2025-08-08"
        if (LocalDate.now().isAfter(deliveryDate)) {
            throw new IllegalArgumentException("Delivery date cannot be in the past");
        }
        return ProductEntity.builder()
                .sellerName(createDTO.getSellerName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(createDTO.getPrice())
                .discountWithCard(resultsOfDiscountWithCard)
                .discountWithoutCard(resultsOfDiscountWithoutCard)
                .deliveryDate(deliveryDate)
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY UPDATE
    public ProductEntity toUpdateProductEntity(String productId, ProductCreateDTO createDTO) {
        int productPrice = Integer.parseInt(createDTO.getPrice());
        int discountWithCard = createDTO.getDiscountWithCard();
        int discountWithoutCard = createDTO.getDiscountWithoutCard();
        int resultsOfDiscountWithCard = 0;
        int resultsOfDiscountWithoutCard = 0;
        if (productPrice > 0) {
            resultsOfDiscountWithCard = productPrice % discountWithCard;
            resultsOfDiscountWithoutCard = productPrice % discountWithoutCard;
        }

        LocalDate deliveryDate = LocalDate.parse(createDTO.getDeliveryDate()); // "2025-08-08"
        if (LocalDate.now().isAfter(deliveryDate)) {
            throw new IllegalArgumentException("Delivery date cannot be in the past");
        }
        return ProductEntity.builder()
                .productId(productId)
                .sellerName(createDTO.getSellerName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(createDTO.getPrice())
                .discountWithCard(resultsOfDiscountWithCard)
                .discountWithoutCard(resultsOfDiscountWithoutCard)
                .deliveryDate(deliveryDate)
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public ProductDTO toProductDTO(ProductEntity product) {
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

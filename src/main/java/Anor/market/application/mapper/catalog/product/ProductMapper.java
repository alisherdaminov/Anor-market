package Anor.market.application.mapper.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ProductMapper {


    /// DTO TO ENTITY
    public ProductEntity toProductEntity(ProductCreateDTO createDTO) {
        BigDecimal productPrice = createDTO.getPrice();
        BigDecimal discountWithCard = createDTO.getDiscountWithCard();
        BigDecimal discountWithoutCard = createDTO.getDiscountWithoutCard();

        if (productPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        // Percent of product's discount
        BigDecimal discountWithCardPercent = BigDecimal.ZERO;
        BigDecimal discountWithoutCardPercent = BigDecimal.ZERO;

        //with card discount
        if (discountWithCard != null && discountWithCard.compareTo(BigDecimal.ZERO) > 0) {
            discountWithCardPercent = productPrice.subtract(discountWithCard)
                    .divide(productPrice, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }
        //without card discount
        if (discountWithoutCard != null && discountWithoutCard.compareTo(BigDecimal.ZERO) > 0) {
            discountWithoutCardPercent = productPrice.subtract(discountWithoutCard)
                    .divide(productPrice, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }

        // after discount price
        BigDecimal priceAfterCardDiscount = productPrice.subtract(
                productPrice.multiply(discountWithCardPercent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
        // after discount price
        BigDecimal priceAfterNoCardDiscount = productPrice.subtract(
                productPrice.multiply(discountWithoutCardPercent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));

        LocalDate deliveryDate = LocalDate.parse(createDTO.getDeliveryDate()); // "2025-08-08"
        if (LocalDate.now().isAfter(deliveryDate)) {
            throw new IllegalArgumentException("Delivery date cannot be in the past");
        }

        return ProductEntity.builder()
                .sellerName(createDTO.getSellerName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(productPrice)
                .discountWithCardPercent(discountWithCardPercent)
                .discountWithoutCardPercent(discountWithoutCardPercent)
                .discountWithCard(priceAfterCardDiscount)
                .discountWithoutCard(priceAfterNoCardDiscount)
                .deliveryDate(deliveryDate)
                .localDateTime(LocalDateTime.now())
                .build();
    }


    /// DTO TO ENTITY UPDATE
    public ProductEntity toUpdateProductEntity(String productId, ProductCreateDTO createDTO) {
        BigDecimal productPrice = createDTO.getPrice();
        BigDecimal discountWithCard = createDTO.getDiscountWithCard();
        BigDecimal discountWithoutCard = createDTO.getDiscountWithoutCard();

        if (productPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        // Percent of product's discount
        BigDecimal discountWithCardPercent = BigDecimal.ZERO;
        BigDecimal discountWithoutCardPercent = BigDecimal.ZERO;

        //with card discount
        if (discountWithCard != null && discountWithCard.compareTo(BigDecimal.ZERO) > 0) {
            discountWithCardPercent = productPrice.subtract(discountWithCard)
                    .divide(productPrice, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }
        //without card discount
        if (discountWithoutCard != null && discountWithoutCard.compareTo(BigDecimal.ZERO) > 0) {
            discountWithoutCardPercent = productPrice.subtract(discountWithoutCard)
                    .divide(productPrice, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }

        // after discount price
        BigDecimal priceAfterCardDiscount = productPrice.subtract(
                productPrice.multiply(discountWithCardPercent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
        // after discount price
        BigDecimal priceAfterNoCardDiscount = productPrice.subtract(
                productPrice.multiply(discountWithoutCardPercent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));

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
                .price(productPrice)
                .discountWithCardPercent(discountWithCardPercent)
                .discountWithoutCardPercent(discountWithoutCardPercent)
                .discountWithCard(priceAfterCardDiscount)
                .discountWithoutCard(priceAfterNoCardDiscount)
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
                .discountWithCardPercent(product.getDiscountWithCardPercent())
                .discountWithCard(product.getDiscountWithCard())
                .discountWithoutCardPercent(product.getDiscountWithoutCardPercent())
                .discountWithoutCard(product.getDiscountWithoutCard())
                .deliveryDate(product.getDeliveryDate())
                .localDateTime(product.getLocalDateTime())
                .build();
    }
}

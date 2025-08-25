package Anor.market.application.mapper.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.dto.catalog.product.images.ProductImageDTO;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
public class ProductMapper {


    /// DTO TO ENTITY
    public ProductEntity toProductEntity(ProductCreateDTO createDTO) {
        int price = createDTO.getPrice();
        int percentOfDiscountWithCard = createDTO.getDiscountWithCardPercent();
        int percentOfDiscountWithoutCard = createDTO.getDiscountWithoutCardPercent();
        int divideByHundred = 100;

        int discountedWithCard = price * percentOfDiscountWithCard / divideByHundred;
        int discountedWithoutCard = price * percentOfDiscountWithoutCard / divideByHundred;

        LocalDate deliveryDate = LocalDate.parse(createDTO.getDeliveryDate()); // "2025-08-08"
        if (LocalDate.now().isAfter(deliveryDate)) {
            throw new IllegalArgumentException("Delivery date cannot be in the past");
        }

        return ProductEntity.builder()
                .sellerName(createDTO.getSellerName())
                .productName(createDTO.getProductName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(price)
                .discountWithCardPercent(percentOfDiscountWithCard)
                .discountPriceWithCard(discountedWithCard)
                .discountWithoutCardPercent(percentOfDiscountWithoutCard)
                .discountPriceWithoutCard(discountedWithoutCard)
                .deliveryDate(deliveryDate)
                .localDateTime(LocalDateTime.now())
                .build();
    }


    /// DTO TO ENTITY UPDATE
    public ProductEntity toUpdateProductEntity(String productId, ProductCreateDTO createDTO) {
        int price = createDTO.getPrice();
        int percentOfDiscountWithCard = createDTO.getDiscountWithCardPercent();
        int percentOfDiscountWithoutCard = createDTO.getDiscountWithoutCardPercent();
        int divideByHundred = 100;

        int discountedWithCard = price * percentOfDiscountWithCard / divideByHundred;
        int discountedWithoutCard = price * percentOfDiscountWithoutCard / divideByHundred;

        LocalDate deliveryDate = LocalDate.parse(createDTO.getDeliveryDate()); // "2025-08-08"
        if (LocalDate.now().isAfter(deliveryDate)) {
            throw new IllegalArgumentException("Delivery date cannot be in the past");
        }
        return ProductEntity.builder()
                .productId(productId)
                .sellerName(createDTO.getSellerName())
                .productName(createDTO.getProductName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(price)
                .discountWithCardPercent(percentOfDiscountWithCard)
                .discountPriceWithCard(discountedWithCard)
                .discountWithoutCardPercent(percentOfDiscountWithoutCard)
                .discountPriceWithoutCard(discountedWithoutCard)
                .deliveryDate(deliveryDate)
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public ProductDTO toProductDTO(ProductEntity product) {
        List<ProductImageDTO> imageDTOs = product.getImages().stream()
                .sorted(Comparator.comparingInt(ProductImageEntity::getSortOrder))
                .map(img -> ProductImageDTO.builder()
                        .imageId(img.getImageId())
                        .url("/api/products/" + product.getProductId() + "/images/" + img.getImageId())
                        .contentType(img.getContentType())
                        .sizeBytes(img.getSizeBytes())
                        .sortOrder(img.getSortOrder())
                        .build())
                .toList();
        return ProductDTO.builder()
                .productId(product.getProductId())
                .sellerName(product.getSellerName())
                .productName(product.getProductName())
                .deliveryTitle(product.getDeliveryTitle())
                .productDescription(product.getProductDescription())
                .productColor(product.getProductColor())
                .price(product.getPrice())
                .discountWithCardPercent(product.getDiscountWithCardPercent())
                .discountPriceWithCard(product.getDiscountPriceWithCard())
                .discountWithoutCardPercent(product.getDiscountWithoutCardPercent())
                .discountPriceWithoutCard(product.getDiscountPriceWithoutCard())
                .deliveryDate(product.getDeliveryDate())
                .localDateTime(product.getLocalDateTime())
                .images(imageDTOs)
                .build();
    }
}

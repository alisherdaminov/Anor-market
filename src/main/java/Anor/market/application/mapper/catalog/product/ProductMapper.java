package Anor.market.application.mapper.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.mapper.catalog.product.image.ProductImageMapper;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ProductMapper {

    @Autowired
    private ProductImageMapper productImageMapper;

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
    public ProductDTO toProductDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .productId(productEntity.getProductId())
                .sellerName(productEntity.getSellerName())
                .productName(productEntity.getProductName())
                .deliveryTitle(productEntity.getDeliveryTitle())
                .productDescription(productEntity.getProductDescription())
                .productColor(productEntity.getProductColor())
                .price(productEntity.getPrice())
                .discountWithCardPercent(productEntity.getDiscountWithCardPercent())
                .discountPriceWithCard(productEntity.getDiscountPriceWithCard())
                .discountWithoutCardPercent(productEntity.getDiscountWithoutCardPercent())
                .discountPriceWithoutCard(productEntity.getDiscountPriceWithoutCard())
                .deliveryDate(productEntity.getDeliveryDate())
                .localDateTime(productEntity.getLocalDateTime())
                .images(productImageMapper.productImageDTOList(productEntity))
                .build();
    }
}
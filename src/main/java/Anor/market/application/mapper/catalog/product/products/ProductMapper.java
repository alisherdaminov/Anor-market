package Anor.market.application.mapper.catalog.product.products;

import Anor.market.application.dto.catalog.product.products.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import Anor.market.application.mapper.catalog.product.comments.CommentsMapper;
import Anor.market.application.mapper.catalog.product.image.ProductImageMapper;
import Anor.market.domain.model.catalog.product.products.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private CommentsMapper commentsMapper;

    /// DTO TO ENTITY
    public ProductEntity toProductEntity(ProductCreateDTO createDTO) {
        int price = createDTO.getPrice();
        int percentOfDiscountWithCard = createDTO.getDiscountWithCardPercent();
        int percentOfDiscountWithoutCard = createDTO.getDiscountWithoutCardPercent();
        int divideByHundred = 100;

        int discountedWithCard = price * percentOfDiscountWithCard / divideByHundred;
        int discountedWithoutCard = price * percentOfDiscountWithoutCard / divideByHundred;

        return ProductEntity.builder()
                .productName(createDTO.getProductName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(price)
                .discountWithCardPercent(percentOfDiscountWithCard)
                .discountPriceWithCard(discountedWithCard)
                .discountWithoutCardPercent(percentOfDiscountWithoutCard)
                .discountPriceWithoutCard(discountedWithoutCard)
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY UPDATE
    public ProductEntity toUpdateProductEntity(UUID productId, ProductCreateDTO createDTO) {
        int price = createDTO.getPrice();
        int percentOfDiscountWithCard = createDTO.getDiscountWithCardPercent();
        int percentOfDiscountWithoutCard = createDTO.getDiscountWithoutCardPercent();
        int divideByHundred = 100;

        int discountedWithCard = price * percentOfDiscountWithCard / divideByHundred;
        int discountedWithoutCard = price * percentOfDiscountWithoutCard / divideByHundred;

        return ProductEntity.builder()
                .productId(productId)
                .productName(createDTO.getProductName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(price)
                .discountWithCardPercent(percentOfDiscountWithCard)
                .discountPriceWithCard(discountedWithCard)
                .discountWithoutCardPercent(percentOfDiscountWithoutCard)
                .discountPriceWithoutCard(discountedWithoutCard)
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
                .comments(
                        productEntity.getCommentsEntityList() != null
                                ? productEntity.getCommentsEntityList()
                                .stream()
                                .map(commentsMapper::toDTO)
                                .collect(Collectors.toList())
                                : Collections.emptyList()
                )
                .images(productImageMapper.productImageDTOList(productEntity))
                .build();
    }
}
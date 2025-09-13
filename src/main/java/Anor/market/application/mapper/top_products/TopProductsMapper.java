package Anor.market.application.mapper.top_products;

import Anor.market.application.dto.top_products.top_products.create.TopProductsCreateDTO;
import Anor.market.application.dto.top_products.top_products.dto.TopProductsDTO;
import Anor.market.application.dto.top_products.top_products.update.TopProductsUpdateDTO;
import Anor.market.application.mapper.catalog.product.comments.CommentsMapper;
import Anor.market.application.mapper.catalog.product.image.ProductImageMapper;
import Anor.market.domain.model.top_products.TopProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Component
public class TopProductsMapper {

    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private CommentsMapper commentsMapper;

    /// DTO TO ENTITY
    public TopProductsEntity toEntity(TopProductsCreateDTO createDTO) {
        return TopProductsEntity.builder()
                .isTopProductStartDate(createDTO.getIsTopProductStartDate())
                .isTopProductEndDate(createDTO.getIsTopProductEndDate())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY
    public TopProductsEntity toEntityUpdate(UUID topProductsId, TopProductsUpdateDTO createDTO) {
        return TopProductsEntity.builder()
                .topProductsId(topProductsId)
                .sellerName(createDTO.getSellerName())
                .productName(createDTO.getProductName())
                .deliveryTitle(createDTO.getDeliveryTitle())
                .productDescription(createDTO.getProductDescription())
                .productColor(createDTO.getProductColor())
                .price(createDTO.getPrice())
                .discountWithCardPercent(createDTO.getDiscountWithCardPercent())
                .discountPriceWithCard(createDTO.getDiscountPriceWithCard())
                .discountWithoutCardPercent(createDTO.getDiscountWithoutCardPercent())
                .discountPriceWithoutCard(createDTO.getDiscountPriceWithoutCard())
                .deliveryDate(createDTO.getDeliveryDate())
                .localDateTime(createDTO.getLocalDateTime())
                .isTopProductStartDate(createDTO.getIsTopProductStartDate())
                .isTopProductEndDate(createDTO.getIsTopProductEndDate())
                .isTopProductStartDate(createDTO.getIsTopProductStartDate())
                .isTopProductEndDate(createDTO.getIsTopProductEndDate())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public TopProductsDTO toDTO(TopProductsEntity entity) {
        return TopProductsDTO.builder()
                .topProductsId(entity.getTopProductsId())
                .sellerName(entity.getSellerName())
                .productName(entity.getProductName())
                .deliveryTitle(entity.getDeliveryTitle())
                .productDescription(entity.getProductDescription())
                .productColor(entity.getProductColor())
                .price(entity.getPrice())
                .discountWithCardPercent(entity.getDiscountWithCardPercent())
                .discountPriceWithCard(entity.getDiscountPriceWithCard())
                .discountWithoutCardPercent(entity.getDiscountWithoutCardPercent())
                .discountPriceWithoutCard(entity.getDiscountPriceWithoutCard())
                .deliveryDate(entity.getDeliveryDate())
                .localDateTime(entity.getLocalDateTime())
                .isTopProductStartDate(entity.getIsTopProductStartDate())
                .isTopProductEndDate(entity.getIsTopProductEndDate())
                .images(Optional.ofNullable(productImageMapper.topProductsImageList(entity)).orElse(Collections.emptyList()))
                .build();
    }
}

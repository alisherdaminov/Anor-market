package Anor.market.application.mapper.home.home_product;

import Anor.market.application.dto.home.home_product.HomeProductsDTO;
import Anor.market.application.mapper.catalog.product.comments.CommentsMapper;
import Anor.market.application.mapper.catalog.product.image.ProductImageMapper;
import Anor.market.domain.model.home.home_product.HomeProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;


@Component
public class HomeProductsMapper {

    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private CommentsMapper commentsMapper;

    /// DTO TO ENTITY
    public HomeProductsDTO toDTO(HomeProductsEntity products) {
        return HomeProductsDTO.builder()
                .productId(products.getProductId())
                .sellerName(products.getSellerName())
                .productName(products.getProductName())
                .deliveryTitle(products.getDeliveryTitle())
                .productDescription(products.getProductDescription())
                .productColor(products.getProductColor())
                .price(products.getPrice())
                .discountWithCardPercent(products.getDiscountWithCardPercent())
                .discountPriceWithCard(products.getDiscountPriceWithCard())
                .discountWithoutCardPercent(products.getDiscountWithoutCardPercent())
                .discountPriceWithoutCard(products.getDiscountPriceWithoutCard())
                .deliveryDate(products.getDeliveryDate())
                .createdAt(products.getCreatedAt())
                .comments(products.getCommentsEntityList() != null
                        ? products.getCommentsEntityList()
                        .stream()
                        .map(commentsMapper::toDTO)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .images(productImageMapper.homeProductsList(products))
                .build();
    }
}

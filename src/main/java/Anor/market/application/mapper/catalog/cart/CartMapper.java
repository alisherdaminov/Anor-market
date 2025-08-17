package Anor.market.application.mapper.catalog.cart;


import Anor.market.application.dto.catalog.cart.create.CartCreateDTO;
import Anor.market.application.dto.catalog.cart.dto.CartDTO;
import Anor.market.application.mapper.catalog.product.ProductMapper;
import Anor.market.domain.model.entity.catalog.cart.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartMapper {

    @Autowired
    private ProductMapper productMapper;

    /// DTO TO ENTITY
    public CartEntity toEntity(CartCreateDTO createDTO) {
        if (createDTO == null) return null;

        return CartEntity.builder()
                .productId(createDTO.getProductId())
                .quantityOfProduct(createDTO.getQuantityOfProduct())
                .localDateTime(LocalDateTime.now())
                .build();
    }


    /// ENTITY TO DTO
    public CartDTO toDTO(CartEntity cartEntity) {
        if (cartEntity == null) return null;

        return CartDTO.builder()
                .cartId(cartEntity.getCartId())
                .quantityOfProduct(cartEntity.getQuantityOfProduct())
                .product(productMapper.toProductDTO(cartEntity.getProductEntityCart()))
                .localDateTime(cartEntity.getLocalDateTime())
                .build();
    }
}

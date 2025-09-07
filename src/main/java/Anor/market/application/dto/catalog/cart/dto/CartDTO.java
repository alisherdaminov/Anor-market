package Anor.market.application.dto.catalog.cart.dto;

import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {

    private UUID cartId;
    private ProductDTO product;
    private int quantityOfProduct;
    private LocalDateTime localDateTime;
}

package Anor.market.application.dto.catalog.cart.dto;

import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {

    private String cartId;
    private ProductDTO product;
    private int quantityOfProduct;
    private LocalDateTime localDateTime;
}

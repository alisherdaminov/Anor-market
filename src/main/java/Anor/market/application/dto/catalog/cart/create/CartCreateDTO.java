package Anor.market.application.dto.catalog.cart.create;

import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartCreateDTO {

    private String productId;
    private int quantityOfProduct;
}

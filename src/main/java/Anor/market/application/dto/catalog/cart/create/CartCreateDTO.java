package Anor.market.application.dto.catalog.cart.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartCreateDTO {

    private UUID productId;
    private int quantityOfProduct;
}

package Anor.market.application.dto.catalog.cart.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

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

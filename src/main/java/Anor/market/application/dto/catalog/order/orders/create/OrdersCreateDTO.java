package Anor.market.application.dto.catalog.order.orders.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersCreateDTO {


    private String productId;
    private String branchId;
    private String cardId;
}

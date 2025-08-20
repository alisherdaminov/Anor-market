package Anor.market.application.dto.catalog.order.orders.create;

import Anor.market.application.dto.catalog.order.branch.create.BranchesCreateDTO;
import Anor.market.application.dto.catalog.order.cards.create.CardsCreateDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersCreateDTO {

    private String branchTitle;
    private int deliveryPrice;
    private BranchesCreateDTO branchesCreate;
    private CardsCreateDTO cardsCreate;
}

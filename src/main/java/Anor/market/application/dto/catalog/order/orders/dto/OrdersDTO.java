package Anor.market.application.dto.catalog.order.orders.dto;

import Anor.market.application.dto.catalog.order.branch.dto.BranchesDTO;
import Anor.market.application.dto.catalog.order.cards.CardsDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersDTO {

    private String ordersId;
    private String branchTitle;
    private String consumerName;
    private String consumerPhoneNumber;
    private String deliveryTitle;
    private int productPrice;
    private int deliveryPrice;
    private int discountProductPrice;
    private int overallPrice;
    private BranchesDTO branches;
    private CardsDTO cards;
    private LocalDateTime localDateTime;
}

package Anor.market.application.dto.catalog.order.orders.dto;

import Anor.market.application.dto.catalog.order.branch.dto.BranchesDTO;
import Anor.market.application.dto.catalog.order.cards.CardsDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersDTO {

    private UUID ordersId;
    private String productId;
    private String branchTitle;
    private String consumerName;
    private String consumerPhoneNumber;
    private String deliveryTitle;
    private String branchName;
    private String cardName;
    private int productPrice;
    private int discountProductPrice;
    private int overallPrice;
    private BranchesDTO branches;
    private CardsDTO cards;
    private LocalDateTime localDateTime;
}

package Anor.market.application.dto.catalog.order.cards.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardsCreateDTO {

    private String cardName;
}

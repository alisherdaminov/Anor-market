package Anor.market.application.dto.catalog.order.cards;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardsDTO {

    private UUID cardsId;
    private String cardName;
    private LocalDateTime localDateTime;
}

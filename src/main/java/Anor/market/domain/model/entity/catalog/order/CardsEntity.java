package Anor.market.domain.model.entity.catalog.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CardsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cardsId;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //OrdersService
    @Column(name = "order_cards_id")
    private String orderCardsId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_cards_id", insertable = false, updatable = false)
    private OrdersEntity ordersEntityCards;
}

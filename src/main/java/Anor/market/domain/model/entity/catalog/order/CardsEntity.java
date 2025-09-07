package Anor.market.domain.model.entity.catalog.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private UUID cardsId;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Orders
    @OneToMany(mappedBy = "cardsEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrdersEntity> ordersList = new ArrayList<>();
}

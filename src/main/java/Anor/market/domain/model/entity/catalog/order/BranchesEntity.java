package Anor.market.domain.model.entity.catalog.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "branch")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BranchesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String branchId;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();


    //OrdersService
    @Column(name = "order_id")
    private String orderId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrdersEntity ordersEntityBranches;
}

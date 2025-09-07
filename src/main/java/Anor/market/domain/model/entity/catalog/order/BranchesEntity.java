package Anor.market.domain.model.entity.catalog.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Column(name = "branches_id")
    private UUID branchesId;
    @Column(name = "branch_title")
    private String branchTitle;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();


    //Orders
    @OneToMany(mappedBy = "branchesEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrdersEntity> ordersList = new ArrayList<>();
}

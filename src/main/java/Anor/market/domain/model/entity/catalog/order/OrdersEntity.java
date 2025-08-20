package Anor.market.domain.model.entity.catalog.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ordersId;
    @Column(name = "branch_title")
    private String branchTitle;
    @Column(name = "consumer_name")
    private String consumerName;
    @Column(name = "consumer_phone_number")
    private String consumerPhoneNumber;
    @Column(name = "delivery_title")
    private String deliveryTitle;
    @Column(name = "product_price")
    private int productPrice;
    @Column(name = "delivery_price")
    private int deliveryPrice;
    @Column(name = "discount_product_price")
    private int discountProductPrice;
    @Column(name = "overall_price")
    private int overallPrice;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Branches
    @OneToOne(mappedBy = "ordersEntityBranches", fetch = FetchType.LAZY)
    private BranchesEntity branchesEntity;

    //Cards
    @OneToOne(mappedBy = "ordersEntityCards", fetch = FetchType.LAZY)
    private CardsEntity cardsEntity;


}

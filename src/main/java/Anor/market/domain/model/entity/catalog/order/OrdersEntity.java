package Anor.market.domain.model.entity.catalog.order;

import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "product_price")
    private int productPrice;
    @Column(name = "discount_product_price")
    private int discountProductPrice;
    @Column(name = "overall_price")
    private int overallPrice;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Branches
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branches_id")
    private BranchesEntity branchesEntity;

    //Cards
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards_id")
    private CardsEntity cardsEntity;

    //Products
    @Column(name = "products_orders_id")
    private String productOfOrdersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_orders_id", insertable = false, updatable = false)
    private ProductEntity productEntityOrders;

    //Users
    @Column(name = "user_orders_id")
    private Integer ordersUserId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_orders_id", insertable = false, updatable = false)
    private UserEntity userEntityOrders;


}

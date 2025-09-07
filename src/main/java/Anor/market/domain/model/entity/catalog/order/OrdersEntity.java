package Anor.market.domain.model.entity.catalog.order;

import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID ordersId;
    @Column(name = "branch_title_orders")
    private String branchTitleOrders;
    @Column(name = "consumer_name_orders")
    private String consumerName;
    @Column(name = "consumer_phone_number_orders")
    private String consumerPhoneNumber;
    @Column(name = "delivery_title_orders")
    private String deliveryTitle;
    @Column(name = "branch_name_orders")
    private String branchName;
    @Column(name = "card_name_orders")
    private String cardName;
    @Column(name = "product_price_orders")
    private int productPrice;
    @Column(name = "discount_product_price_orders")
    private int discountProductPrice;
    @Column(name = "overall_price_orders")
    private int overallPrice;
    @Column(name = "local_date_time_orders")
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
    private UUID productOfOrdersId;

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

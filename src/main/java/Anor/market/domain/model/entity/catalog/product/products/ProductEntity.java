package Anor.market.domain.model.entity.catalog.product.products;

import Anor.market.domain.model.entity.catalog.cart.CartEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import Anor.market.domain.model.entity.catalog.favorite.FavoriteEntity;
import Anor.market.domain.model.entity.catalog.order.OrdersEntity;
import Anor.market.domain.model.entity.catalog.product.comments.CommentsEntity;
import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    @Column(name = "seller_name")
    private String sellerName;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "delivery_title")
    private String deliveryTitle;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_color")
    private String productColor;
    @Column(name = "price")
    private int price;
    @Column(name = "discount_with_card_percent")
    private int discountWithCardPercent;
    @Column(name = "discount__price_with_card")
    private int discountPriceWithCard;
    @Column(name = "discount_without_card_percent")
    private int discountWithoutCardPercent;
    @Column(name = "discount_price_without_card")
    private int discountPriceWithoutCard;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //CategoryItemList
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_item_list_id")
    private CategoryItemListEntity categoryItemListEntity;

    //Carts
    @OneToOne(mappedBy = "productEntityCart", fetch = FetchType.LAZY)
    private CartEntity cartEntity;

    //Favorite
    @OneToOne(mappedBy = "productEntityFavorite", fetch = FetchType.LAZY)
    private FavoriteEntity favorites;

    //Orders
    @OneToMany(mappedBy = "productEntityOrders", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrdersEntity> ordersEntityList = new ArrayList<>();

    //Images
    @OneToMany(mappedBy = "productEntityImage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImageEntity> images = new ArrayList<>();

    //Comments
    @OneToMany(mappedBy = "productEntityComments", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentsEntity> commentsEntityList = new ArrayList<>();


}

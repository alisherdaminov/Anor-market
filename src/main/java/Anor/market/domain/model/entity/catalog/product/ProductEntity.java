package Anor.market.domain.model.entity.catalog.product;

import Anor.market.domain.model.entity.catalog.cart.CartEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import Anor.market.domain.model.entity.catalog.favorite.FavoriteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column(name = "delivery_title")
    private String deliveryTitle;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_color")
    private String productColor;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discount_with_card_percent")
    private BigDecimal discountWithCardPercent;
    @Column(name = "discount_with_card")
    private BigDecimal discountWithCard;
    @Column(name = "discount_without_card_percent")
    private BigDecimal discountWithoutCardPercent;
    @Column(name = "discount_without_card")
    private BigDecimal discountWithoutCard;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //CategoryItemList
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_item_list_id")
    private CategoryItemListEntity categoryItemListEntity;

    //Carts
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_products_id")
    private CartEntity cartEntity;

    //Favorite
    @OneToOne(mappedBy = "productEntityFavorite", fetch = FetchType.LAZY)
    private FavoriteEntity favorites;

}

package Anor.market.domain.model.entity.catalog.cart;

import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;
    @Column(name = "quantity_of_product")
    private int quantityOfProduct;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Products
    @Column(name = "products_cart_id")
    private UUID productId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_cart_id", insertable = false, updatable = false)
    private ProductEntity productEntityCart;


}

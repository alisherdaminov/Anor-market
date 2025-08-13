package Anor.market.domain.model.entity.catalog.cart;

import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private String cartId;
    @Column(name = "quantity_of_product")
    private int quantityOfProduct;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Products
    @OneToMany(mappedBy = "cartEntity", fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList;


}

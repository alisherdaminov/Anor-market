package Anor.market.domain.model.entity.catalog;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
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
    private String price;
    @Column(name = "discount_with_card")
    private int discountWithCard;
    @Column(name = "discount_without_card")
    private int discountWithoutCard;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_item_list_id")
    private CategoryItemListEntity categoryItemListEntity;
}

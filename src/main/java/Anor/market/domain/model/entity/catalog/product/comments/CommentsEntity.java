package Anor.market.domain.model.entity.catalog.product.comments;

import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String commentsId;
    @Column(name = "commenters_name")
    private String commentersName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "advantage_of_product")
    private String advantageOfProduct;
    @Column(name = "disadvantage_of_product")
    private String disadvantageOfProduct;
    @Column(name = "comments")
    private String comments;
    @Column(name = "local_date")
    private LocalDate localDate = LocalDate.now();

    //Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_comment_id")
    private ProductEntity productEntityComments;
}

package Anor.market.domain.model.catalog.catalog;

import Anor.market.domain.model.catalog.product.products.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "category_items_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryItemListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryItemListId;
    @Column(name = "category_item_list_name")
    private String categoryItemListName;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    //ProductEntity
    @OneToMany(mappedBy = "categoryItemListEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductEntity> productEntityList;
}

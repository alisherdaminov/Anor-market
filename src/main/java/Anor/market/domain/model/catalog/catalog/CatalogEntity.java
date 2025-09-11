package Anor.market.domain.model.catalog.catalog;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "catalogs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID catalogId;
    @Column(name = "catalog_name")
    private String catalogName;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    //Category
    @OneToMany(mappedBy = "catalogEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryEntity> categoryEntityList;
}

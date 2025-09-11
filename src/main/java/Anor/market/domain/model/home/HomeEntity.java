package Anor.market.domain.model.home;


import Anor.market.domain.model.home.home_product.HomeProductsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "homes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "home_id")
    private UUID homeId;
    @Column(name = "home_title")
    private String homeTitle;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    //Home
    @OneToMany(mappedBy = "homeEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HomeProductsEntity> homeProductsEntityList = new ArrayList<>();

}

package Anor.market.domain.model.auth;

import Anor.market.domain.model.catalog.order.OrdersEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "is_gender")
    private boolean isGender;
    @Column(name = "is_seller")
    private boolean isSeller;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    //Roles
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // One user can have many roles
    private List<RolesEntity> roles;

    //Orders
    @OneToOne(mappedBy = "userEntityOrders")
    private OrdersEntity ordersEntityUser;

    //RefreshToken
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RefreshTokenEntity> refreshToken;
}

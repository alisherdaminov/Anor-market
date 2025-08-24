package Anor.market.domain.model.entity.auth;

import Anor.market.domain.model.entity.catalog.order.OrdersEntity;
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
    @Column(name = "isGender")
    private boolean isGender;
    @Column(name = "isSeller")
    private boolean isSeller;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();


    //Roles
    @OneToOne(mappedBy = "userEntityRoles")
    private RolesEntity rolesEntityUser;

    //Orders
    @OneToOne(mappedBy = "userEntityOrders")
    private OrdersEntity ordersEntityUser;

    //RefreshToken
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RefreshTokenEntity> refreshToken;
}

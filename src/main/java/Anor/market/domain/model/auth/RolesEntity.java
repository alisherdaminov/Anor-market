package Anor.market.domain.model.auth;

import Anor.market.shared.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID rolesId;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private Roles rolesEnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @Column(name = "created_at")
    private LocalDateTime localDateTime = LocalDateTime.now();
}

package Anor.market.domain.model.entity.auth;

import Anor.market.shared.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private String rolesId;

    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Roles rolesEnum;

    @Column(name = "user_roles_id")
    private Integer rolesUserId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_roles", insertable = false, updatable = false)
    private UserEntity userEntityRoles;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime = LocalDateTime.now();
}

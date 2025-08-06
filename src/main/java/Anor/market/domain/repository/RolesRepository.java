package Anor.market.domain.repository;

import Anor.market.domain.model.entity.auth.RolesEntity;
import Anor.market.shared.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, String> {

    @Query("SELECT r.rolesEnum FROM RolesEntity r WHERE r.rolesUserId = ?1")
    Roles findByRoles(Integer userId);
}

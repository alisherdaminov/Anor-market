package Anor.market.domain.repository.auth;

import Anor.market.domain.model.auth.RolesEntity;
import Anor.market.shared.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, UUID> {

    @Query("SELECT r FROM RolesEntity r WHERE r.user.userId = :userId")
    List<Roles> findAllByUserId(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM RolesEntity c WHERE c.rolesId = :rolesId")
    int deleteByRoleId(@Param("rolesId") UUID rolesId);


}

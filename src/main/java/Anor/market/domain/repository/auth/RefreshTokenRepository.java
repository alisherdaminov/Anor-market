package Anor.market.domain.repository.auth;

import Anor.market.domain.model.entity.auth.RefreshTokenEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    @Transactional
    @Modifying
    @Query("DELETE FROM RefreshTokenEntity r WHERE r.user.userId = :userId AND r.refreshToken = :refreshToken")
    void deleteByUserIdAndToken(@Param("userId") Integer userId, @Param("refreshToken") String refreshToken);

    default Optional<RefreshTokenEntity> findByStringId(String id){
        return findById(UUID.fromString(id));
    }

}

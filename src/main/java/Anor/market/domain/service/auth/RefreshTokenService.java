package Anor.market.domain.service.auth;

import Anor.market.domain.model.auth.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenService {


    RefreshTokenEntity createRefreshToken(Integer userId);

    Optional<RefreshTokenEntity> findByToken(String refreshToken);

    RefreshTokenEntity isValidToken(RefreshTokenEntity entity);

    void deleteRefreshToken(Integer userId, String refreshToken);
}

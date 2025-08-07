package Anor.market.application.service.auth;

import Anor.market.domain.model.entity.auth.RefreshTokenEntity;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.repository.auth.RefreshTokenRepository;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.service.auth.RefreshTokenService;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static Anor.market.infrastucture.config.validation.JwtTokens.generateRefreshToken;

/**
 * RefreshTokenServiceImpl implements RefreshTokenService and creating refresh token for getting new access token, finding by userid
 * isValidToken is going to check whether the access token is still valid or not,
 * once no need to use refresh token that by using of delete refresh token will be deleting!
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    /// CREATE REFRESH TOKEN
    @Override
    public RefreshTokenEntity createRefreshToken(Integer userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setUser(userEntity);
        refreshTokenEntity.setRefreshToken(generateRefreshToken());
        long refreshExpirationMs = 1000L * 60 * 60 * 24 * 7;
        refreshTokenEntity.setExpiryDate(Instant.now().plusMillis(refreshExpirationMs));
        return refreshTokenRepository.save(refreshTokenEntity);
    }

    /// GET BY TOKEN
    @Override
    public Optional<RefreshTokenEntity> findByToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    /// FOR CHECKING - IS TOKEN STILL VALID?
    @Override
    public RefreshTokenEntity isValidToken(RefreshTokenEntity entity) {
        if (entity.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(entity);
            throw new AppBadException("Refresh token is expired!");
        }
        return entity;
    }

    /// DELETE REFRESH TOKEN BY USER ID AND REFRESH TOKEN
    @Override
    public void deleteRefreshToken(Integer userId, String refreshToken) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        refreshTokenRepository.deleteByUserIdAndToken(userEntity.getUserId(), refreshToken);
    }
}

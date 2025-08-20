package Anor.market.presentation.controller.auth;

import Anor.market.application.dto.auth.create.UserCreatedDTO;
import Anor.market.application.dto.auth.dto.UserDTO;
import Anor.market.application.service.auth.AuthServiceImpl;
import Anor.market.application.service.auth.RefreshTokenServiceImpl;
import Anor.market.domain.model.entity.auth.RefreshTokenEntity;
import Anor.market.infrastucture.config.validation.JwtTokens;
import Anor.market.presentation.request.LoginCreatedDTO;
import Anor.market.presentation.request.LogoutRequest;
import Anor.market.presentation.request.RefreshTokenRequest;
import Anor.market.presentation.response.AppResponse;
import Anor.market.shared.exceptions.AppBadException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
public class Auth {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @PostMapping("/registration")
    public ResponseEntity<AppResponse<String>> userRegistration(@Valid @RequestBody UserCreatedDTO userCreatedDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(authService.userRegistration(userCreatedDTO), "success", new Date()));
    }

    @PostMapping("/login")
    public ResponseEntity<AppResponse<UserDTO>> login(@Valid @RequestBody LoginCreatedDTO loginCreatedDTO) {
        return ResponseEntity.ok().body(authService.login(loginCreatedDTO));
    }

    @PutMapping("/user-details/{userId}")
    public ResponseEntity<AppResponse<UserDTO>> userRegistration(
            @Valid
            @PathVariable("userId") Integer userId,
            @RequestBody UserCreatedDTO userCreatedDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(authService.update(userId, userCreatedDTO), "success", new Date()));
    }


    @PostMapping("/refresh")
    public ResponseEntity<AppResponse<String>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshTokenRequest();

        // refresh token is valid still
        RefreshTokenEntity entity = refreshTokenService.findByToken(refreshToken).map(refreshTokenService::isValidToken)
                .orElseThrow(() -> new AppBadException("Refresh token is not found!"));

        // generating new token
        String generatedToken = JwtTokens.encode(entity.getUser().getEmail(), entity.getUser().getUserId(),
                entity.getUser().getRolesEntityUser().getRolesEnum());
        return ResponseEntity.ok().body(new AppResponse<>(generatedToken, "success", new Date()));
    }

    @DeleteMapping("/log-out")
    public ResponseEntity<AppResponse<String>> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        return ResponseEntity.ok().body(new AppResponse<>(authService.logout(logoutRequest.getUserId(), logoutRequest.getRefreshToken()),
                "success", new Date()));
    }

}
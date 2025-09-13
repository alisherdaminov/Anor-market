package Anor.market.application.mapper.auth;

import Anor.market.application.dto.auth.create.UserCreatedDTO;
import Anor.market.application.dto.auth.dto.UserDTO;
import Anor.market.application.service.auth.RefreshTokenServiceImpl;
import Anor.market.domain.model.auth.UserEntity;
import Anor.market.domain.repository.auth.RolesRepository;
import Anor.market.infrastucture.config.validation.JwtTokens;
import Anor.market.shared.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    /// DTO TO ENTITY
    public UserEntity toEntity(UserCreatedDTO userCreatedDTO) {
        return UserEntity.builder()
                .lastName(userCreatedDTO.getLastName())
                .firstName(userCreatedDTO.getFirstName())
                .email(userCreatedDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(userCreatedDTO.getPassword()))
                .phoneNumber(userCreatedDTO.getPhoneNumber())
                .isGender(userCreatedDTO.isGender())
                .isSeller(userCreatedDTO.isSeller())
                .createdAt(LocalDateTime.now())
                .build();
    }

    /// DTO TO UPDATE ENTITY
    public UserEntity toUpdateEntity(Integer userId, UserCreatedDTO userCreatedDTO) {
        return UserEntity.builder()
                .userId(userId)
                .lastName(userCreatedDTO.getLastName())
                .firstName(userCreatedDTO.getFirstName())
                .email(userCreatedDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(userCreatedDTO.getPassword()))
                .phoneNumber(userCreatedDTO.getPhoneNumber())
                .isGender(userCreatedDTO.isGender())
                .isSeller(userCreatedDTO.isSeller())
                .createdAt(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public UserDTO toDTO(UserEntity user) {
        List<Roles> rolesList = Optional.ofNullable(rolesRepository.findAllByUserId(user.getUserId()))
                .orElse(Collections.emptyList())
                .stream()
                .map(role -> Roles.valueOf(role.name()))
                .collect(Collectors.toList());
//        if (rolesList.isEmpty()) {
//            rolesList = Collections.singletonList(Roles.USER); // default role
//        }
        String jwt = JwtTokens.encode(user.getEmail(), user.getUserId(), rolesList);
        String refresh = refreshTokenService.createRefreshToken(user.getUserId()).getRefreshToken();

        return UserDTO.builder()
                .userId(user.getUserId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isGender(user.isGender())
                .isSeller(user.isSeller())
                .roles(rolesList)
                .jwtToken(jwt)
                .refreshToken(refresh)
                .createdAt(user.getCreatedAt())
                .build();

    }



}

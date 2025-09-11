package Anor.market.application.service.auth;

import Anor.market.application.dto.auth.create.UserCreatedDTO;
import Anor.market.application.dto.auth.dto.UserDTO;
import Anor.market.application.mapper.auth.AuthMapper;
import Anor.market.domain.model.auth.UserEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.service.auth.AuthService;
import Anor.market.presentation.request.LoginCreatedDTO;
import Anor.market.presentation.response.AppResponse;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * this is AuthServiceImpl implements AuthService and users can register and log in after successful registrations
 * Then, The user has got a few opportunities to update and log out of the system
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesServiceImpl rolesService;
    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthMapper authMapper;


    /// CREATE USER REGISTRATION
    @Override
    public String userRegistration(UserCreatedDTO userCreatedDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userCreatedDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new AppBadException("User exists!");
        }
        UserEntity user = authMapper.toEntity(userCreatedDTO);
        userRepository.save(user);
        rolesService.createRole(user, Roles.USER);
        return "User successfully registered!";
    }

    /// GET USER LOG IN
    @Override
    public AppResponse<UserDTO> login(LoginCreatedDTO loginCreatedDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(loginCreatedDTO.getEmail());
        if (optionalUser.isEmpty()) {
            throw new AppBadException("User is not found!");
        }
        UserEntity user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(loginCreatedDTO.getPassword(), user.getPassword())) {
            return new AppResponse<>("Wrong password!");
        }
        UserDTO userDTO = authMapper.toDTO(user);
        return new AppResponse<>(userDTO, "success", new Date());
    }

    /// UPDATE USER DETAILS
    @Override
    public UserDTO update(Integer userId, UserCreatedDTO userCreatedDTO) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new AppBadException("User is not found!");
        }
        UserEntity user = authMapper.toUpdateEntity(userId, userCreatedDTO);
        userRepository.save(user);
        return authMapper.toDTO(user);
    }

    /// DELETE USER LOG OUT
    @Override
    public String logout(Integer userId, String refreshToken) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        refreshTokenService.deleteRefreshToken(userEntity.getUserId(), refreshToken);
        return "Deleted!";
    }
}

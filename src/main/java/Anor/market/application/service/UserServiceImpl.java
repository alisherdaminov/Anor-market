package Anor.market.application.service;

import Anor.market.application.dto.auth.create.UserCreatedDTO;
import Anor.market.application.dto.auth.dto.UserDTO;
import Anor.market.application.mapper.auth.AuthMapper;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.repository.RolesRepository;
import Anor.market.domain.repository.UserRepository;
import Anor.market.domain.service.UserService;
import Anor.market.infrastucture.config.validation.JwtTokens;
import Anor.market.presentation.request.LoginCreatedDTO;
import Anor.market.presentation.response.AppResponse;
import Anor.market.presentation.response.LoginResponseDTO;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * this is UserServiceImpl implements UserService and users can register and log in after successful registrations
 * Then, The user has got a few opportunities to update and log out of the system
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private RolesServiceImpl rolesService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RolesRepository rolesRepository;

    /// CREATE USER REGISTRATION
    @Override
    public AppResponse<UserDTO> userRegistration(UserCreatedDTO userCreatedDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userCreatedDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new AppBadException("User exists!");
        }
        UserEntity user = authMapper.toEntity(userCreatedDTO);
        userRepository.save(user);
        rolesService.createRole(user.getUserId(), Roles.USER);
        return authMapper.toDTO(user);
    }

    /// GET USER LOG IN
    @Override
    public AppResponse<LoginResponseDTO> login(LoginCreatedDTO loginCreatedDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(loginCreatedDTO.getEmail());
        if (optionalUser.isEmpty()) {
            throw new AppBadException("User is not found!");
        }
        UserEntity user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(loginCreatedDTO.getPassword(), user.getPassword())) {
            return new AppResponse<>("Wrong password!");
        }
        LoginResponseDTO dto = new LoginResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setGender(user.isGender());
        dto.setSeller(user.isSeller());
        Roles roles = rolesRepository.findByRoles(user.getUserId());
        dto.setRoles(roles);
        dto.setJwtToken(JwtTokens.encode(user.getEmail(), user.getUserId(), roles));
        //dto.setRefreshToken(refreshTokenService.createRefreshToken(user.getUserId()).getRefreshToken());
        return new AppResponse<>();
    }

    /// UPDATE USER DETAILS
    @Override
    public AppResponse<UserDTO> update(Integer userId, UserCreatedDTO userCreatedDTO) {
        userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        UserEntity userEntity = authMapper.toEntity(userCreatedDTO);
        userRepository.save(userEntity);
        return authMapper.toDTO(userEntity);
    }

    /// DELETE USER LOG OUT
    @Override
    public void logout(Integer userId, String refreshToken) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
    }
}

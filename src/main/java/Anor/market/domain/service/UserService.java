package Anor.market.domain.service;

import Anor.market.application.dto.auth.create.UserCreatedDTO;
import Anor.market.application.dto.auth.dto.UserDTO;
import Anor.market.presentation.request.LoginCreatedDTO;
import Anor.market.presentation.response.AppResponse;
import Anor.market.presentation.response.LoginResponseDTO;

public interface UserService {

    AppResponse<UserDTO> userRegistration(UserCreatedDTO userCreatedDTO);

    AppResponse<LoginResponseDTO> login(LoginCreatedDTO loginCreatedDTO);

    AppResponse<UserDTO> update(Integer userId, UserCreatedDTO userCreatedDTO);

    void logout(Integer userId, String refreshToken);

}

package Anor.market.domain.service.auth;

import Anor.market.application.dto.auth.create.UserCreatedDTO;
import Anor.market.application.dto.auth.dto.UserDTO;
import Anor.market.presentation.request.LoginCreatedDTO;
import Anor.market.presentation.response.AppResponse;

public interface AuthService {

    String userRegistration(UserCreatedDTO userCreatedDTO);

    AppResponse<UserDTO> login(LoginCreatedDTO loginCreatedDTO);

    UserDTO update(Integer userId, UserCreatedDTO userCreatedDTO);

    String logout(Integer userId, String refreshToken);

}

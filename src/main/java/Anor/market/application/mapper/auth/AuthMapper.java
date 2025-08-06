package Anor.market.application.mapper.auth;

import Anor.market.application.dto.auth.create.UserCreatedDTO;
import Anor.market.application.dto.auth.dto.UserDTO;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.presentation.response.AppResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AppResponse<UserDTO> toDTO(UserEntity userEntity);

    UserEntity toEntity(UserCreatedDTO userCreatedDTO);

}

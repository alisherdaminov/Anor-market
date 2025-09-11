package Anor.market.domain.service.auth;


import Anor.market.domain.model.auth.UserEntity;
import Anor.market.shared.enums.Roles;

public interface RolesService {

    void createRole(UserEntity userId, Roles roles);
}

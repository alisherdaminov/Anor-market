package Anor.market.domain.service;


import Anor.market.shared.enums.Roles;

public interface RolesService {

    void createRole(Integer userId, Roles roles);
}

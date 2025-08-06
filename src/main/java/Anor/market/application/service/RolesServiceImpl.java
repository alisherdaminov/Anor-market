package Anor.market.application.service;

import Anor.market.domain.model.entity.auth.RolesEntity;
import Anor.market.domain.repository.RolesRepository;
import Anor.market.domain.service.RolesService;
import Anor.market.shared.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
/**
 * RolesServiceImpl implements RolesService and creating new role of the users with their own id as well as setting
 * their roles with current time
 * */
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    /// CREATE A ROLE
    @Override
    public void createRole(Integer userId, Roles roles) {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRolesUserId(userId);
        rolesEntity.setRolesEnum(roles);
        rolesEntity.setLocalDateTime(LocalDateTime.now());
        rolesRepository.save(rolesEntity);
    }
}

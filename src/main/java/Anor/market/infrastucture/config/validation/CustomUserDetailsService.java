package Anor.market.infrastucture.config.validation;

import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.repository.auth.RolesRepository;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new AppBadException("User is not found!");
        }
        UserEntity userEntity = optionalUser.get();

        List<Roles> roles = rolesRepository.findAllByUserId(userEntity.getUserId());
        return new CustomUserDetails(userEntity, roles);
    }
}

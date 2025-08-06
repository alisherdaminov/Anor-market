package Anor.market.infrastucture.config.validation;

import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.shared.enums.Roles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private Integer userId;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isGender;
    private boolean isSeller;
    private GrantedAuthority authority;

    public CustomUserDetails(UserEntity user, Roles roles) {
        this.userId = user.getUserId();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.isGender = user.isGender();
        this.isSeller = user.isSeller();
        this.authority = new SimpleGrantedAuthority(roles.name());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

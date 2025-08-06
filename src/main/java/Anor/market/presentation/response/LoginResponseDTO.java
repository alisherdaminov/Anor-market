package Anor.market.presentation.response;

import Anor.market.shared.enums.Roles;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoginResponseDTO {

    private Integer userId;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isGender;
    private boolean isSeller;
    private Roles roles;
    private String jwtToken;
    private String refreshToken;
    private LocalDateTime createdAt;
}

package Anor.market.application.dto.auth.dto;

import Anor.market.shared.enums.Roles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Integer userId;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isGender;
    private boolean isSeller;
    private List<Roles> roles;
    private String jwtToken;
    private String refreshToken;
    private LocalDateTime createdAt;

}

package Anor.market.application.dto.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}

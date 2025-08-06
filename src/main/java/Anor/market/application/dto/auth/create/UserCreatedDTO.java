package Anor.market.application.dto.auth.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedDTO {

    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isGender;
    private boolean isSeller;

}

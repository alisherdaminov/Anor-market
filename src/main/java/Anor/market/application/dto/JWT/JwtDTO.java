package Anor.market.application.dto.JWT;

import Anor.market.shared.enums.Roles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtDTO {

    private Integer userId;
    private String email;
    private Roles roles;
}

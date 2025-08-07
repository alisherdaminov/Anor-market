package Anor.market.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequest {
    private Integer userId;
    private String refreshToken;
}


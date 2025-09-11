package Anor.market.application.dto.home.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeCreateDTO {

    private String homeTitle;
    private int discountPercent;
}

package Anor.market.application.dto.top_products.top_products.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopProductsCreateDTO {

    private String productId;
    private LocalDate isTopProductStartDate;
    private LocalDate isTopProductEndDate;

}

package Anor.market.application.dto.catalog.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCreateDTO {

    private String sellerName;
    private String deliveryTitle;
    private String productDescription;
    private String productColor;
    private String price;
    private int discountWithCard;
    private int discountWithoutCard;
    private String deliveryDate;
}

package Anor.market.application.dto.top_products.top_products.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopProductsUpdateDTO {

    private String sellerName;
    private String productName;
    private String deliveryTitle;
    private String productDescription;
    private String productColor;
    private int price;
    private int discountWithCardPercent;
    private int discountPriceWithCard;
    private int discountWithoutCardPercent;
    private int discountPriceWithoutCard;
    private LocalDate deliveryDate;
    private LocalDate isTopProductStartDate;
    private LocalDate isTopProductEndDate;
    private LocalDateTime localDateTime;
}

package Anor.market.application.dto.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private String productId;
    private String sellerName;
    private String deliveryTitle;
    private String productDescription;
    private String productColor;
    private String price;
    private int discountWithCard;
    private int discountWithoutCard;
    private LocalDate deliveryDate;
    private LocalDateTime localDateTime;
}

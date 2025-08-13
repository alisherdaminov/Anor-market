package Anor.market.application.dto.catalog.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
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
    private BigDecimal price;
    private BigDecimal discountWithCardPercent;
    private BigDecimal discountWithCard;
    private BigDecimal discountWithoutCardPercent;
    private BigDecimal discountWithoutCard;
    private LocalDate deliveryDate;
    private LocalDateTime localDateTime;
}

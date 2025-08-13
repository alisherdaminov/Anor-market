package Anor.market.application.dto.catalog.product.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;


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
    private BigDecimal price;
    private BigDecimal discountWithCardPercent;
    private BigDecimal discountWithCard;
    private BigDecimal discountWithoutCardPercent;
    private BigDecimal discountWithoutCard;
    private boolean isLiked;
    private boolean isCart;
    private int quantityOfProduct;
    private String deliveryDate;
}

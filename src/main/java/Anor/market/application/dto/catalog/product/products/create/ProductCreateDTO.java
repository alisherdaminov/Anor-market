package Anor.market.application.dto.catalog.product.products.create;

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

    private String categoryItemListId;
    private String sellerName;
    private String productName;
    private String deliveryTitle;
    private String productDescription;
    private String productColor;
    private int price;
    private int discountWithCardPercent;
    private int discountWithoutCardPercent;
    private String deliveryDate;
}

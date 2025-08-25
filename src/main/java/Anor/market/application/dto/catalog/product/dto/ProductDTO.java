package Anor.market.application.dto.catalog.product.dto;

import Anor.market.application.dto.catalog.product.images.ProductImageDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private String productId;
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
    private LocalDateTime localDateTime;
    private List<ProductImageDTO> images;
}

package Anor.market.application.dto.home.home_product;



import Anor.market.application.dto.catalog.product.comments.dto.CommentsDTO;
import Anor.market.application.dto.catalog.product.images.ProductImageDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeProductsDTO {

    private UUID productId;
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
    private LocalDateTime createdAt;
    private List<CommentsDTO> comments;
    private List<ProductImageDTO> images;
}

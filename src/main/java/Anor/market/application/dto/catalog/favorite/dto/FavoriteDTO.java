package Anor.market.application.dto.catalog.favorite.dto;

import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoriteDTO {

    private String favoriteId;
    private ProductDTO productDTO;
    private LocalDateTime localDateTime;
}

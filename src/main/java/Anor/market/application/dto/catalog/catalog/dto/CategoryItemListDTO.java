package Anor.market.application.dto.catalog.catalog.dto;

import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryItemListDTO {

    private UUID categoryItemListId;
    private String categoryItemListName;
    private LocalDateTime localDateTime;
    private List<ProductDTO> productList;
}

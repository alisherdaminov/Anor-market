package Anor.market.application.dto.catalog.catalog.create;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryItemListCreateDTO {

    private String categoryItemListName;
    private List<ProductCreateDTO> productList;
}

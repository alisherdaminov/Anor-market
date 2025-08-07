package Anor.market.application.dto.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryItemListDTO {

    private String categoryItemListId;
    private String categoryItemListName;
    private LocalDateTime localDateTime;
    private List<ProductDTO> productList;
}

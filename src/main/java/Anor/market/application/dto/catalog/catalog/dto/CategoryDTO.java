package Anor.market.application.dto.catalog.catalog.dto;

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
public class CategoryDTO {

    private String categoryId;
    private String categoryName;
    private LocalDateTime localDateTime;
    private List<CategoryItemListDTO> categoryItemList;
}

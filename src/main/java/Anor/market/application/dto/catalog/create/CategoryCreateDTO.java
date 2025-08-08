package Anor.market.application.dto.catalog.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreateDTO {

    private String categoryName;
    private List<CategoryItemListCreateDTO> categoryItemListCreateList;
}

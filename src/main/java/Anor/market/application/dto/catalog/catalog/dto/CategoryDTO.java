package Anor.market.application.dto.catalog.catalog.dto;

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
public class CategoryDTO {

    private UUID categoryId;
    private String categoryName;
    private LocalDateTime localDateTime;
    private List<CategoryItemListDTO> categoryItemList;
}

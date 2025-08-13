package Anor.market.application.dto.catalog.catalog.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogCreateDTO {

    private String catalogName;
    private List<CategoryCreateDTO> categoryCreateList;
}

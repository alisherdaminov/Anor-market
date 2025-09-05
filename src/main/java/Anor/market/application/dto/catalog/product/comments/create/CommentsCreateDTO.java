package Anor.market.application.dto.catalog.product.comments.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentsCreateDTO {

    private String advantageOfProduct;
    private String disadvantageOfProduct;
    private String comments;
}

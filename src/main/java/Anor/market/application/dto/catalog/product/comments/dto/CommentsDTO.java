package Anor.market.application.dto.catalog.product.comments.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentsDTO {

    private String commentsId;
    private String commentersName;
    private String productDescription;
    private String advantageOfProduct;
    private String disadvantageOfProduct;
    private String comments;
    private LocalDate localDate;
}

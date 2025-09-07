package Anor.market.application.dto.catalog.product.comments.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentsDTO {

    private UUID commentsId;
    private String commentersName;
    private String productDescription;
    private String advantageOfProduct;
    private String disadvantageOfProduct;
    private String comments;
    private LocalDate localDate;
}

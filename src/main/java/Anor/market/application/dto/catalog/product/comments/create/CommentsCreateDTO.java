package Anor.market.application.dto.catalog.product.comments.create;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsCreateDTO {

    private String advantageOfProduct;
    private String disadvantageOfProduct;
    private String comments;
}

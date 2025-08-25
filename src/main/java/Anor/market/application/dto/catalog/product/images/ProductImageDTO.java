package Anor.market.application.dto.catalog.product.images;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductImageDTO {
    private String imageId;
    private String url;
    private String contentType;
    private long sizeBytes;
    private int sortOrder;
}

package Anor.market.application.dto.catalog.product.images;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductImageDTO {

    private String imageId;
    private String origenName;
    private String extension;
    private String path;
    private Long size;
    private String url;
    private LocalDateTime createdAt;
}

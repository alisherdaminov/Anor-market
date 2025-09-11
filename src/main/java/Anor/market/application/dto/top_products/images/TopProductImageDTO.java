package Anor.market.application.dto.top_products.images;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopProductImageDTO {

    private UUID imageId;
    private String origenName;
    private String extension;
    private String path;
    private Long size;
    private String url;
    private LocalDateTime createdAt;
}

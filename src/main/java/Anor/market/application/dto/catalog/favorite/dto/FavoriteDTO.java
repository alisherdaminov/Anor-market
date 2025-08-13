package Anor.market.application.dto.catalog.favorite.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoriteDTO {

    private String favoriteId;
    private String productId;
    private LocalDateTime localDateTime;
}

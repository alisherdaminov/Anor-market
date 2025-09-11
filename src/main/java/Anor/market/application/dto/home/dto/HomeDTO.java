package Anor.market.application.dto.home.dto;

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
public class HomeDTO {

    private UUID homeId;
    private String homeTitle;
    private LocalDateTime createdAt;
}

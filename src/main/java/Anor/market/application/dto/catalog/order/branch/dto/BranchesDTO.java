package Anor.market.application.dto.catalog.order.branch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchesDTO {

    private UUID branchId;
    private String branchTitle;
    private String cityName;
    private LocalDateTime localDateTime;
}

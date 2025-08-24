package Anor.market.application.mapper.catalog.order;

import Anor.market.application.dto.catalog.order.branch.create.BranchesCreateDTO;
import Anor.market.application.dto.catalog.order.branch.dto.BranchesDTO;
import Anor.market.domain.model.entity.catalog.order.BranchesEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BranchesMapper {

    /// DTO TO ENTITY
    public BranchesEntity toEntity(BranchesCreateDTO createDTO) {
        return BranchesEntity.builder()
                .branchTitle(createDTO.getBranchTitle())
                .cityName(createDTO.getCityName())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public BranchesDTO toDTO(BranchesEntity entity) {
        return BranchesDTO.builder()
                .branchId(entity.getBranchId())
                .branchTitle(entity.getBranchTitle())
                .cityName(entity.getCityName())
                .localDateTime(entity.getLocalDateTime())
                .build();
    }
}

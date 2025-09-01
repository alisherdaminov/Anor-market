package Anor.market.application.mapper.catalog.product.comments;

import Anor.market.application.dto.catalog.product.comments.create.CommentsCreateDTO;
import Anor.market.application.dto.catalog.product.comments.dto.CommentsDTO;
import Anor.market.domain.model.entity.catalog.product.comments.CommentsEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommentsMapper {

    /// ENTITY TO DTO
    public CommentsEntity toEntity(CommentsCreateDTO createDTO) {
        return CommentsEntity.builder()
                .advantageOfProduct(createDTO.getAdvantageOfProduct())
                .disadvantageOfProduct(createDTO.getDisadvantageOfProduct())
                .comments(createDTO.getComments())
                .localDate(LocalDate.now())
                .build();
    }

    /// DTO TO ENTITY
    public CommentsDTO toDTO(CommentsEntity entity) {
        return CommentsDTO.builder()
                .commentsId(entity.getCommentsId())
                .commentersName(entity.getCommentersName())
                .productDescription(entity.getProductDescription())
                .advantageOfProduct(entity.getAdvantageOfProduct())
                .disadvantageOfProduct(entity.getDisadvantageOfProduct())
                .comments(entity.getComments())
                .localDate(entity.getLocalDate())
                .build();

    }

}

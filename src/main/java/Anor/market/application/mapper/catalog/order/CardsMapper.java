package Anor.market.application.mapper.catalog.order;

import Anor.market.application.dto.catalog.order.cards.CardsDTO;
import Anor.market.application.dto.catalog.order.cards.create.CardsCreateDTO;
import Anor.market.domain.model.entity.catalog.order.CardsEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CardsMapper {

    /// DTO TO ENTITY
    public CardsEntity toEntity(CardsCreateDTO createDTO) {
        return CardsEntity.builder()
                .cardName(createDTO.getCardName())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public CardsDTO toDTO(CardsEntity entity) {
        return CardsDTO.builder()
                .cardName(entity.getCardName())
                .localDateTime(entity.getLocalDateTime())
                .build();
    }
}

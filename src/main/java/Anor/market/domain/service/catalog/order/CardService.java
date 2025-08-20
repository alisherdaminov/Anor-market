package Anor.market.domain.service.catalog.order;

import Anor.market.application.dto.catalog.order.cards.CardsDTO;
import Anor.market.application.dto.catalog.order.cards.create.CardsCreateDTO;

import java.util.List;

public interface CardService {

    CardsDTO createCards(CardsCreateDTO createDTO);

    List<CardsDTO> getAllCards();

    CardsDTO updateCard(String cardId, CardsCreateDTO createDTO);

    String deleteCardById(String cardId);

}

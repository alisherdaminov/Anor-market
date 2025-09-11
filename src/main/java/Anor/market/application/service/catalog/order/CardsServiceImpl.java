package Anor.market.application.service.catalog.order;

import Anor.market.application.dto.catalog.order.cards.CardsDTO;
import Anor.market.application.dto.catalog.order.cards.create.CardsCreateDTO;
import Anor.market.application.mapper.catalog.order.CardsMapper;
import Anor.market.domain.model.catalog.order.CardsEntity;
import Anor.market.domain.repository.catalog.order.CardsRepository;
import Anor.market.domain.service.catalog.order.CardService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CardsServiceImpl implements CardService which has CRUD operation to create cards different types of bank cards
 * this is created by ADMIN,and using by other customers and even for the sellers
 *
 */
@Service
public class CardsServiceImpl implements CardService {

    @Autowired
    private CardsRepository cardsRepository;
    @Autowired
    private CardsMapper cardsMapper;

    /// CREATE A CARD
    @Override
    public CardsDTO createCards(CardsCreateDTO createDTO) {
        if (SpringSecurityValid.hasRole(Roles.USER) && SpringSecurityValid.hasRole(Roles.SELLER)) {
            throw new AppBadException("You do not have any permission to make a card!");
        }
        CardsEntity cardsEntity = cardsMapper.toEntity(createDTO);
        cardsRepository.save(cardsEntity);
        return cardsMapper.toDTO(cardsEntity);
    }

    /// GET ALL CARDS DATA
    @Override
    public List<CardsDTO> getAllCards() {
        return cardsRepository.findAll(Sort.by(Sort.Direction.DESC, "localDateTime")).stream().map(cardsMapper::toDTO).toList();
    }

    /// UPDATE ALL CARDS DATA BY ID
    @Override
    public CardsDTO updateCard(String cardId, CardsCreateDTO createDTO) {
        if (SpringSecurityValid.hasRole(Roles.USER) && SpringSecurityValid.hasRole(Roles.SELLER)) {
            throw new AppBadException("You do not have any permission to make a card!");
        }
        CardsEntity cardsEntity = cardsRepository.findByStringId(cardId).orElseThrow(() -> new AppBadException("Card id is not found!"));
        cardsEntity.setCardName(createDTO.getCardName());
        cardsRepository.save(cardsEntity);
        return cardsMapper.toDTO(cardsEntity);
    }

    /// DELETE CARDS BY ID
    @Override
    public String deleteCardById(String cardId) {
        cardsRepository.deleteByCardsId(cardId);
        return "Deleted!";
    }

}

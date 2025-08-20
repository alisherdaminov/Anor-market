package Anor.market.presentation.controller.catalog.order;

import Anor.market.application.dto.catalog.order.cards.CardsDTO;
import Anor.market.application.dto.catalog.order.cards.create.CardsCreateDTO;
import Anor.market.application.service.catalog.order.CardsServiceImpl;
import Anor.market.presentation.response.AppResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class Cards {

    @Autowired
    private CardsServiceImpl cardsService;

    @PostMapping
    public ResponseEntity<AppResponse<CardsDTO>> createCards(
            @Valid
            @RequestBody CardsCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(cardsService.createCards(createDTO), "success", new Date()));
    }

    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<CardsDTO>>> getAllCards() {
        return ResponseEntity.ok().body(new AppResponse<>(cardsService.getAllCards(), "success", new Date()));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<AppResponse<CardsDTO>> updateCard(
            @Valid
            @PathVariable("cardId") String cardId,
            @RequestBody CardsCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(cardsService.updateCard(cardId, createDTO), "success", new Date()));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<AppResponse<String>> deleteCardById(
            @Valid
            @PathVariable("cardId") String cardId) {
        return ResponseEntity.ok().body(new AppResponse<>(cardsService.deleteCardById(cardId), "success", new Date()));
    }

}

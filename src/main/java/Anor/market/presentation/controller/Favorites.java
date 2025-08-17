package Anor.market.presentation.controller;

import Anor.market.application.dto.catalog.favorite.create.FavoriteCreateDTO;
import Anor.market.application.dto.catalog.favorite.dto.FavoriteDTO;
import Anor.market.application.service.catalog.favorite.FavoriteServiceImpl;
import Anor.market.presentation.response.AppResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class Favorites {

    @Autowired
    private FavoriteServiceImpl service;


    @PostMapping
    public ResponseEntity<AppResponse<FavoriteDTO>> createFavorite(
            @Valid
            @RequestBody FavoriteCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.createFavorite(createDTO), "success", new Date()));
    }

    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<FavoriteDTO>>> getAllFavorite() {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAllFavorites(), "success", new Date()));
    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<AppResponse<String>> deleteFavorite(
            @Valid
            @PathVariable("favoriteId") String favoriteId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteFavorite(favoriteId), "success", new Date()));
    }
}

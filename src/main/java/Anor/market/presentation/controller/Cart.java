package Anor.market.presentation.controller;

import Anor.market.application.dto.catalog.cart.create.CartCreateDTO;
import Anor.market.application.dto.catalog.cart.dto.CartDTO;
import Anor.market.application.service.catalog.cart.CartServiceImpl;
import Anor.market.presentation.response.AppResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class Cart {

    @Autowired
    private CartServiceImpl service;


    @PostMapping
    public ResponseEntity<AppResponse<CartDTO>> createCart(
            @Valid
            @RequestBody CartCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.createCart(createDTO), "success", new Date()));
    }

    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<CartDTO>>> getAllCarts() {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAllCarts(), "success", new Date()));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<AppResponse<String>> deleteCart(
            @Valid
            @PathVariable("cartId") String cartId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteCart(cartId), "success", new Date()));
    }
}

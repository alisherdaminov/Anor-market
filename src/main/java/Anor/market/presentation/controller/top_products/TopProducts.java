package Anor.market.presentation.controller.top_products;

import Anor.market.application.dto.top_products.top_products.create.TopProductsCreateDTO;
import Anor.market.application.dto.top_products.top_products.dto.TopProductsDTO;
import Anor.market.application.dto.top_products.top_products.update.TopProductsUpdateDTO;
import Anor.market.application.service.top_products.top_products.TopProductsServiceImpl;
import Anor.market.presentation.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/top-products")
public class TopProducts {

    @Autowired
    private TopProductsServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AppResponse<TopProductsDTO>> createTopProducts(@RequestBody TopProductsCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.createTopProducts(createDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<TopProductsDTO>>> getAllTopProducts() {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAllTopProducts(), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{topProductsId}")
    public ResponseEntity<AppResponse<TopProductsDTO>> getTopProductsById(@PathVariable("topProductsId") UUID topProductsId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.getTopProductsById(topProductsId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{topProductsId}")
    public ResponseEntity<AppResponse<TopProductsDTO>> updateTopProducts(
            @PathVariable("topProductsId") UUID topProductsId,
            @RequestBody TopProductsUpdateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.updateTopProducts(topProductsId, createDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{topProductsId}")
    public ResponseEntity<AppResponse<String>> deleteTopProductsById(@PathVariable("topProductsId") String topProductsId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteTopProductsById(topProductsId), "success", new Date()));
    }
}

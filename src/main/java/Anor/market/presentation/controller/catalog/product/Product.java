package Anor.market.presentation.controller.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.service.catalog.product.ProductServiceImpl;
import Anor.market.presentation.response.AppResponse;
import Anor.market.shared.util.PageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/product")
public class Product {

    @Autowired
    private ProductServiceImpl service;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/{categoryItemListId}")
    public ResponseEntity<AppResponse<ProductDTO>> createProduct(
            @Valid
            @PathVariable("categoryItemListId") String categoryItemListId,
            @RequestBody ProductCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.createProduct(categoryItemListId, createDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/list")
    public ResponseEntity<AppResponse<Page<ProductDTO>>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAll(PageUtil.page(page), size), "success", new Date()));
    }

    @PreAuthorize("hasRole('SELLER')")
    @PutMapping("/{productId}")
    public ResponseEntity<AppResponse<ProductDTO>> updateProduct(
            @Valid
            @PathVariable("productId") String productId,
            @RequestBody ProductCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.updateProduct(productId, createDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<AppResponse<String>> deleteProduct(
            @Valid
            @PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteProduct(productId), "success", new Date()));
    }
}

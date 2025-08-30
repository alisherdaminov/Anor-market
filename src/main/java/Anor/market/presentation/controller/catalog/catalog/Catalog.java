package Anor.market.presentation.controller.catalog.catalog;

import Anor.market.application.dto.catalog.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.catalog.dto.CatalogDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryItemListDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.service.catalog.catalog.CatalogServiceImpl;
import Anor.market.presentation.response.AppResponse;
import Anor.market.presentation.response.PageResponse;
import Anor.market.shared.util.PageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/catalog")
public class Catalog {

    @Autowired
    private CatalogServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AppResponse<CatalogDTO>> createCatalog(
            @Valid
            @RequestBody CatalogCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.createCatalog(createDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<PageResponse<CatalogDTO>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        Page<CatalogDTO> catalogPage = service.getAll(PageUtil.page(page), size);
        PageResponse<CatalogDTO> pageResponse = new PageResponse<>(catalogPage);
        return ResponseEntity.ok(pageResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<AppResponse<CategoryDTO>> getCategoryById(
            @Valid
            @PathVariable("categoryId") String categoryId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.getCategoryById(categoryId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category-item-list/{categoryItemListId}")
    public ResponseEntity<AppResponse<CategoryItemListDTO>> getCategoryItemListById(
            @Valid
            @PathVariable("categoryItemListId") String categoryItemListId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.getCategoryItemListById(categoryItemListId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/product/{productId}")
    public ResponseEntity<AppResponse<ProductDTO>> getProductById(
            @Valid
            @PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.getProductById(productId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{catalogId}/{productId}")
    public ResponseEntity<AppResponse<CatalogDTO>> updateCatalog(
            @Valid
            @PathVariable("catalogId") String catalogId,
            @PathVariable("productId") String productId,
            @RequestBody CatalogCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.updateCatalog(catalogId, productId, createDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{catalogId}")
    public ResponseEntity<AppResponse<String>> deleteCatalog(
            @Valid
            @PathVariable("catalogId") String catalogId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteCatalog(catalogId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<AppResponse<String>> deleteCategory(
            @Valid
            @PathVariable("categoryId") String categoryId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteCategory(categoryId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/category-item-list/{categoryItemListId}")
    public ResponseEntity<AppResponse<String>> deleteCategoryItemList(
            @Valid
            @PathVariable("categoryItemListId") String categoryItemListId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteCategoryItemList(categoryItemListId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<AppResponse<String>> deleteProduct(
            @Valid
            @PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteProduct(productId), "success", new Date()));
    }
}

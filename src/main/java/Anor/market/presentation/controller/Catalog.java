package Anor.market.presentation.controller;

import Anor.market.application.dto.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.dto.CatalogDTO;
import Anor.market.application.service.catalog.CatalogServiceImpl;
import Anor.market.presentation.response.AppResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public ResponseEntity<AppResponse<List<CatalogDTO>>> getAll() {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAll(), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{catalogId}")
    public ResponseEntity<AppResponse<CatalogDTO>> updateCatalog(
            @Valid
            @PathVariable("catalogId") String catalogId,
            @RequestBody CatalogCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.updateCatalog(catalogId, createDTO), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{catalogId}")
    public ResponseEntity<AppResponse<String>> deleteCatalog(
            @Valid
            @PathVariable("catalogId") String catalogId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteCatalog(catalogId), "success", new Date()));
    }
}

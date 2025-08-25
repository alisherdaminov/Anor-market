package Anor.market.presentation.controller.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.service.catalog.product.ProductServiceImpl;
import Anor.market.presentation.response.AppResponse;
import Anor.market.shared.util.PageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class Product {

    @Autowired
    private ProductServiceImpl service;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(value = "/{categoryItemListId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AppResponse<ProductDTO>> createProduct(
            @PathVariable("categoryItemListId") String categoryItemListId,
            @Valid @RequestPart("product") ProductCreateDTO createDTO,
            @RequestPart("files") List<MultipartFile> files) throws IOException {
        ProductDTO dto = service.createProduct(categoryItemListId, createDTO, files);
        return ResponseEntity.ok(new AppResponse<>(dto, "success", new Date()));
    }


    @GetMapping("/{productId}/images/{imageId}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String productId,
            @PathVariable String imageId) throws IOException {
        return service.getImage(productId, imageId);
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

    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDTO addImages(
            @PathVariable String id,
            @RequestPart("files") List<MultipartFile> files) throws IOException {
        return service.addImages(id, files);
    }

    @DeleteMapping("/{productId}/images/{imageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeImage(@PathVariable String productId, @PathVariable String imageId) throws IOException {
        service.removeImage(productId, imageId);
    }
}

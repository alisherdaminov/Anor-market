package Anor.market.presentation.controller.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.service.catalog.product.ProductServiceImpl;
import Anor.market.presentation.response.AppResponse;
import Anor.market.presentation.response.PageResponse;
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AppResponse<ProductDTO>> createProduct(
            @RequestPart("product") ProductCreateDTO createDTO,
            @RequestPart("files") List<MultipartFile> files) {
        ProductDTO dto = service.createProduct(createDTO, files);
        return ResponseEntity.ok(new AppResponse<>(dto, "success", new Date()));
    }

    @GetMapping("/{productId}/images/{imageId}")
    public ResponseEntity<Resource> getProductWithImage(
            @PathVariable String productId,
            @PathVariable String imageId) throws IOException {
        return service.getProductWithImage(productId, imageId);
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/list")
    public ResponseEntity<PageResponse<ProductDTO>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        Page<ProductDTO> productDTOPage = service.getAll(PageUtil.page(page), size);
        PageResponse<ProductDTO> pageResponse = new PageResponse<>(productDTOPage);
        return ResponseEntity.ok(pageResponse);
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

    @DeleteMapping("/{productId}/images/{imageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeImage(@PathVariable String productId, @PathVariable String imageId) {
        service.removeImage(productId, imageId);
    }
}

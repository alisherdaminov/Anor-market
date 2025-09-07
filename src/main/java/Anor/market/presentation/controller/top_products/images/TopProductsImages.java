package Anor.market.presentation.controller.top_products.images;

import Anor.market.application.dto.top_products.images.TopProductImageDTO;
import Anor.market.application.service.top_products.images.TopProductImageServiceImpl;
import Anor.market.presentation.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/top-product-image")
public class TopProductsImages {

    @Autowired
    private TopProductImageServiceImpl service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AppResponse<TopProductImageDTO>> saveImage(@RequestPart("files") MultipartFile multipartFile) {
        return ResponseEntity.ok().body(new AppResponse<>(service.saveImage(multipartFile), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{imageId}")
    public ResponseEntity<AppResponse<ResponseEntity<Resource>>> getImage(@PathVariable("imageId") String imageId) throws IOException {
        return ResponseEntity.ok().body(new AppResponse<>(service.getImage(imageId), "success", new Date()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{imageId}")
    public ResponseEntity<AppResponse<String>> removeImage(@PathVariable("imageId") String imageId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.removeImage(imageId), "success", new Date()));
    }
}

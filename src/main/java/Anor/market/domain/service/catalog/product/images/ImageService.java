package Anor.market.domain.service.catalog.product.images;

import Anor.market.application.dto.catalog.product.images.ProductImageDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ImageService {


    List<ProductImageDTO> saveImages(UUID productId, List<MultipartFile> files);

    ResponseEntity<Resource> getImage(String productId, String imageId) throws IOException;

    void removeImage(String productId, String imageId);
}

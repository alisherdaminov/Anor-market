package Anor.market.domain.service.Top_products.images;

import Anor.market.application.dto.top_products.images.TopProductImageDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TopProductImageService {

    TopProductImageDTO saveImage(MultipartFile multipartFile);

    ResponseEntity<Resource> getImage(String imageId) throws IOException;

    String removeImage(String imageId);
}

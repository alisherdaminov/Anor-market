package Anor.market.domain.service.catalog.product.products;

import Anor.market.application.dto.catalog.product.products.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductCreateDTO createDTO, List<MultipartFile> files);

    Page<ProductDTO> getAll(int page, int size);

    ResponseEntity<Resource> getProductWithImage(String productId, String imageId) throws IOException;

    ProductDTO updateProduct(String productId, ProductCreateDTO createDTO);

    String deleteProduct(String productId);

    void removeImage(String productId, String imageId);
}

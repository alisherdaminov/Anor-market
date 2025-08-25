package Anor.market.domain.service.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDTO createProduct(String categoryItemListId, ProductCreateDTO createDTO, List<MultipartFile> files)throws IOException;

    Page<ProductDTO> getAll(int page, int size);

    ProductDTO updateProduct(String productId, ProductCreateDTO createDTO);

    String deleteProduct(String productId);
}

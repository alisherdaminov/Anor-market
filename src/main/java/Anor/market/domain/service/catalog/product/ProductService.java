package Anor.market.domain.service.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductDTO createProduct(String categoryItemListId, ProductCreateDTO createDTO);

    Page<ProductDTO> getAll(int page, int size);

    ProductDTO updateProduct(String productId, ProductCreateDTO createDTO);

    String deleteProduct(String productId);
}

package Anor.market.domain.service.catalog;

import Anor.market.application.dto.catalog.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductCreateDTO createDTO);

    List<ProductDTO> getAll();

    ProductDTO updateProduct(String productId, ProductCreateDTO createDTO);

    String deleteProduct(String productId);
}

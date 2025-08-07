package Anor.market.application.service.catalog;

import Anor.market.application.dto.catalog.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.dto.ProductDTO;
import Anor.market.domain.service.catalog.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public ProductDTO createProduct(ProductCreateDTO createDTO) {
        return null;
    }

    @Override
    public List<ProductDTO> getAll() {
        return List.of();
    }

    @Override
    public ProductDTO updateProduct(String productId, ProductCreateDTO createDTO) {
        return null;
    }

    @Override
    public String deleteProduct(String productId) {
        return "";
    }
}

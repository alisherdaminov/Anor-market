package Anor.market.domain.service.Top_products;

import Anor.market.application.dto.top_products.create.TopProductsCreateDTO;
import Anor.market.application.dto.top_products.dto.TopProductsDTO;
import Anor.market.application.dto.top_products.update.TopProductsUpdateDTO;

import java.util.List;

public interface TopProductsService {

    TopProductsDTO createTopProducts(TopProductsCreateDTO createDTO);

    List<TopProductsDTO> getAllTopProducts();

    TopProductsDTO getTopProductsById(String topProductsId);

    TopProductsDTO updateTopProducts(String topProductsId, TopProductsUpdateDTO createDTO);

    String deleteTopProductsById(String topProductsId);
}

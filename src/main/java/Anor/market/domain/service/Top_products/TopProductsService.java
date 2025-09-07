package Anor.market.domain.service.Top_products;

import Anor.market.application.dto.top_products.top_products.create.TopProductsCreateDTO;
import Anor.market.application.dto.top_products.top_products.dto.TopProductsDTO;
import Anor.market.application.dto.top_products.top_products.update.TopProductsUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface TopProductsService {

    TopProductsDTO createTopProducts(TopProductsCreateDTO createDTO);

    List<TopProductsDTO> getAllTopProducts();

    TopProductsDTO getTopProductsById(UUID topProductsId);

    TopProductsDTO updateTopProducts(UUID topProductsId, TopProductsUpdateDTO createDTO);

    String deleteTopProductsById(String topProductsId);
}

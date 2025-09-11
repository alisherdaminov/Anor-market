package Anor.market.domain.service.home;

import Anor.market.application.dto.home.create.HomeCreateDTO;
import Anor.market.application.dto.home.dto.HomeDTO;
import Anor.market.application.dto.home.home_product.HomeProductsDTO;

import java.util.List;
import java.util.UUID;

public interface HomeService {

    HomeDTO createHome(HomeCreateDTO homeCreateDTO);

    List<HomeProductsDTO> getAllProductsById(String homeTitleId,HomeCreateDTO homeCreateDTO);

    List<HomeDTO> getAllHome();

    HomeDTO updateHome(UUID homeId, HomeCreateDTO homeCreateDTO);

    void deleteHomeById(String homeId);

    void deleteHomeProductById(String homeTitleId);
}

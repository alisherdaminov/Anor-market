package Anor.market.domain.service.home;

import Anor.market.application.dto.home.create.HomeCreateDTO;
import Anor.market.application.dto.home.create.HomeTitleCreateDTO;
import Anor.market.application.dto.home.dto.HomeDTO;
import Anor.market.application.dto.home.home_product.HomeProductsDTO;

import java.util.List;

public interface HomeService {

    HomeDTO createHome(HomeCreateDTO homeCreateDTO);

    List<HomeProductsDTO> getAllProductsById(String homeTitleId, HomeTitleCreateDTO homeTitleCreateDTO);

    List<HomeDTO> getAllHome();

    HomeDTO updateHome(String homeId, HomeCreateDTO homeCreateDTO);

    String deleteHomeById(String homeId);

    String deleteHomeProductById(String homeTitleId);
}

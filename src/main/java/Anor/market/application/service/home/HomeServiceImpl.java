package Anor.market.application.service.home;

import Anor.market.application.dto.home.create.HomeCreateDTO;
import Anor.market.application.dto.home.dto.HomeDTO;
import Anor.market.application.dto.home.home_product.HomeProductsDTO;
import Anor.market.application.mapper.home.HomeMapper;
import Anor.market.application.mapper.home.home_product.HomeProductsMapper;
import Anor.market.domain.model.home.HomeEntity;
import Anor.market.domain.model.home.home_product.HomeProductsEntity;
import Anor.market.domain.repository.home.HomeRepository;
import Anor.market.domain.repository.home.home_product.HomeProductsRepository;
import Anor.market.domain.service.home.HomeService;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {


    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private HomeProductsRepository homeProductsRepository;
    @Autowired
    private HomeMapper homeMapper;
    @Autowired
    private HomeProductsMapper homeProductsMapper;


    @Override
    public HomeDTO createHome(HomeCreateDTO homeCreateDTO) {
        HomeEntity homeEntity = homeMapper.toEntity(homeCreateDTO);
        HomeEntity homeSaved = homeRepository.save(homeEntity);
        return homeMapper.toDTO(homeSaved);
    }

    @Override
    public List<HomeProductsDTO> getAllProductsById(String homeTitleId, HomeCreateDTO homeCreateDTO) {

        HomeEntity homeEntity = homeRepository.findByStringId(homeTitleId).orElseThrow(() -> new AppBadException("Home title id not found!"));

        List<HomeProductsEntity> products = homeProductsRepository.findByHomeEntity_HomeId(homeEntity.getHomeId());

        Comparator<HomeProductsEntity> comparator = switch (homeCreateDTO.getHomeTitle().toLowerCase()) {
            case "price" -> Comparator.comparing(HomeProductsEntity::getPrice);
            case "discount" -> Comparator.comparing(HomeProductsEntity::getDiscountWithCardPercent);
            case "created" -> Comparator.comparing(HomeProductsEntity::getCreatedAt);
            default -> throw new AppBadException("Invalid sort type! Allowed: price, discount, created");
        };
        return products.stream()
                .sorted(comparator)
                .map(homeProductsMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<HomeDTO> getAllHome() {
        return homeRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(homeMapper::toDTO).toList();
    }

    @Override
    public HomeDTO updateHome(UUID homeId, HomeCreateDTO homeCreateDTO) {
        return null;
    }

    @Override
    public void deleteHomeById(String homeId) {
        homeRepository.deleteHomeById(homeId);
    }

    @Override
    public void deleteHomeProductById(String homeTitleId) {
        homeProductsRepository.deleteHomeProductById(homeTitleId);
    }
}

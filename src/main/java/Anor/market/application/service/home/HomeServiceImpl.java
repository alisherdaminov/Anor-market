package Anor.market.application.service.home;

import Anor.market.application.dto.home.create.HomeCreateDTO;
import Anor.market.application.dto.home.create.HomeTitleCreateDTO;
import Anor.market.application.dto.home.dto.HomeDTO;
import Anor.market.application.dto.home.home_product.HomeProductsDTO;
import Anor.market.application.mapper.home.HomeMapper;
import Anor.market.application.mapper.home.home_product.HomeProductsMapper;
import Anor.market.domain.model.catalog.product.products.ProductEntity;
import Anor.market.domain.model.home.HomeEntity;
import Anor.market.domain.model.home.home_product.HomeProductsEntity;
import Anor.market.domain.repository.catalog.product.products.ProductRepository;
import Anor.market.domain.repository.home.HomeRepository;
import Anor.market.domain.repository.home.home_product.HomeProductsRepository;
import Anor.market.domain.service.home.HomeService;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/*
* HomeServiceImpl implements HomeService that all products are sorted by price, discounts, created date and other features
* those are coming form the DATABASE that is presenting in home part only by GET , but POST method is for creating home title name
* and due to that title products will set
* */
@Service
public class HomeServiceImpl implements HomeService {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private HomeProductsRepository homeProductsRepository;
    @Autowired
    private HomeMapper homeMapper;
    @Autowired
    private HomeProductsMapper homeProductsMapper;


    /// CREATE A HOME TITLE
    @Override
    public HomeDTO createHome(HomeCreateDTO homeCreateDTO) {

        HomeEntity homeEntity = homeMapper.toEntity(homeCreateDTO);

        List<ProductEntity> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new AppBadException("Products not found in database!");
        }
        List<HomeProductsEntity> homeProductsList = products.stream().map(product -> {
                    HomeProductsEntity homeProduct = new HomeProductsEntity();
                    //homeProduct.setHomeEntity(homeEntity);
                    product.getImages().forEach(image -> image.setHomeProductsEntity(homeProduct));
                    homeProduct.setSellerName(product.getSellerName());
                    homeProduct.setProductName(product.getProductName());
                    homeProduct.setDeliveryTitle(product.getDeliveryTitle());
                    homeProduct.setProductDescription(product.getProductDescription());
                    homeProduct.setProductColor(product.getProductColor());
                    homeProduct.setPrice(product.getPrice());
                    homeProduct.setDiscountWithCardPercent(product.getDiscountWithCardPercent());
                    homeProduct.setDiscountPriceWithCard(product.getDiscountPriceWithCard());
                    homeProduct.setDiscountWithoutCardPercent(product.getDiscountWithoutCardPercent());
                    homeProduct.setDiscountPriceWithoutCard(product.getDiscountPriceWithoutCard());
                    homeProduct.setDeliveryDate(product.getDeliveryDate());
                    homeProduct.setCreatedAt(LocalDateTime.now());
                    return homeProduct;
                }).sorted(Comparator
                        .comparing(HomeProductsEntity::getPrice)
                        .thenComparing(HomeProductsEntity::getDiscountWithCardPercent)
                        .thenComparing(HomeProductsEntity::getCreatedAt))
                .toList();
        //saved into the DATABASE
        homeProductsRepository.saveAll(homeProductsList);

        homeEntity.setHomeProductsEntityList(homeProductsList);

        //saved into the DATABASE
        homeRepository.save(homeEntity);
        // to DTO
        return homeMapper.toDTO(homeEntity);
    }

    /// GET ALL PRODUCT LIST BY SORTING
    @Override
    public List<HomeProductsDTO> getAllProductsById(String homeTitleId, HomeTitleCreateDTO homeTitleCreateDTO) {

        HomeEntity homeEntity = homeRepository.findByStringId(homeTitleId).orElseThrow(() -> new AppBadException("Home title id not found!"));

        List<HomeProductsEntity> products = homeProductsRepository.findByHomeEntity_HomeId(homeEntity.getHomeId());

        Comparator<HomeProductsEntity> comparator = switch (homeTitleCreateDTO.getHomeTitle().toLowerCase()) {
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

    /// GET ALL HOME PRODUCTS LIST
    @Override
    public List<HomeDTO> getAllHome() {
        return homeRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(homeMapper::toDTO).toList();
    }

    /// UPDATE HOME TITLE BY ID
    @Override
    public HomeDTO updateHome(String homeId, HomeCreateDTO homeCreateDTO) {
        HomeEntity home = homeRepository.findByStringId(homeId).orElseThrow(() -> new AppBadException("Home title id not found!"));
        HomeEntity homeEntity = homeMapper.toUpdateEntity(home.getHomeId(), homeCreateDTO);
        homeRepository.save(homeEntity);
        return homeMapper.toDTO(homeEntity);
    }

    /// DELETE HOME TITLE BY ID
    @Override
    public String deleteHomeById(String homeId) {
        homeRepository.deleteHomeById(homeId);
        return "Deleted!";
    }

    /// DELETE HOME PRODUCT BY ID
    @Override
    public String deleteHomeProductById(String homeProductId) {
        homeProductsRepository.deleteHomeProductById(homeProductId);
        return "Deleted!";
    }
}

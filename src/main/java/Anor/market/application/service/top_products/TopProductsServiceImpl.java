package Anor.market.application.service.top_products;

import Anor.market.application.dto.top_products.create.TopProductsCreateDTO;
import Anor.market.application.dto.top_products.dto.TopProductsDTO;
import Anor.market.application.dto.top_products.update.TopProductsUpdateDTO;
import Anor.market.application.mapper.top_products.TopProductsMapper;
import Anor.market.domain.model.entity.catalog.product.images.ProductImageEntity;
import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import Anor.market.domain.model.entity.top_products.TopProductsEntity;
import Anor.market.domain.repository.catalog.product.image.ProductImageRepository;
import Anor.market.domain.repository.catalog.product.products.ProductRepository;
import Anor.market.domain.repository.top_products.TopProductsRepository;
import Anor.market.domain.service.Top_products.TopProductsService;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopProductsServiceImpl implements TopProductsService {

    @Autowired
    private TopProductsRepository topProductsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private TopProductsMapper topProductsMapper;


    /// CREATE A TOP PRODUCT
    @Override
    public TopProductsDTO createTopProducts(TopProductsCreateDTO createDTO) {
        // find a product by id
        ProductEntity product = productRepository.findById(createDTO.getProductId()).orElseThrow(() -> new AppBadException("Product id is not found!"));
        //creat a top product
        TopProductsEntity topProducts = topProductsMapper.toEntity(createDTO);
        topProducts.setProductEntityTop(product); // <----------> PARENT LINK
        // set a product image &  data's set from the productRepository DATABASE
        List<ProductImageEntity> images = new ArrayList<>(product.getImages());
        images.forEach(productImageEntity -> productImageEntity.setProductEntityImage(product)); // <----------> PARENT LINK
        topProducts.setImages(images);
        topProducts.setSellerName(product.getSellerName());
        topProducts.setProductName(product.getProductName());
        topProducts.setDeliveryTitle(product.getDeliveryTitle());
        topProducts.setProductDescription(product.getProductDescription());
        topProducts.setProductColor(product.getProductColor());
        topProducts.setPrice(product.getPrice());
        topProducts.setDiscountWithCardPercent(product.getDiscountWithCardPercent());
        topProducts.setDiscountPriceWithCard(product.getDiscountPriceWithCard());
        topProducts.setDiscountWithoutCardPercent(product.getDiscountWithoutCardPercent());
        topProducts.setDiscountPriceWithoutCard(product.getDiscountPriceWithoutCard());
        topProducts.setDeliveryDate(product.getDeliveryDate());

        //saved into the DATABASE
        TopProductsEntity saved = topProductsRepository.save(topProducts);
        //to dto
        return topProductsMapper.toDTO(saved);
    }

    /// GET ALL THE TOP PRODUCT'S LIST
    @Override
    public List<TopProductsDTO> getAllTopProducts() {
        return topProductsRepository.findAll(Sort.by(Sort.Direction.DESC, "localDateTime"))
                .stream()
                .filter(entity -> !LocalDate.now().isAfter(entity.getIsTopProductEndDate()))
                .map(topProductsMapper::toDTO)
                .toList();
    }


    /// GET A TOP PRODUCT BY ID
    @Override
    public TopProductsDTO getTopProductsById(String topProductsId) {
        TopProductsEntity topProducts = topProductsRepository.findById(topProductsId).orElseThrow(() -> new AppBadException("Top products id not found!"));
        return topProductsMapper.toDTO(topProducts);
    }

    /// UPDATE A TOP PRODUCT
    @Override
    public TopProductsDTO updateTopProducts(String topProductsId, TopProductsUpdateDTO createDTO) {
        // find a product by id
        TopProductsEntity topProducts = topProductsRepository.findById(topProductsId).orElseThrow(() -> new AppBadException("Top products id not found!"));
        //update
        TopProductsEntity topProductsEntity = topProductsMapper.toEntityUpdate(topProducts.getTopProductsId(), createDTO);
        //updated into the DATABASE
        topProductsRepository.save(topProductsEntity);
        //to dto
        return topProductsMapper.toDTO(topProductsEntity);
    }

    /// DELETE A TOP PRODUCT
    @Override
    public String deleteTopProductsById(String topProductsId) {
        topProductsRepository.deleteById(topProductsId);
        return "Deleted!";
    }
}

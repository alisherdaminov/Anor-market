package Anor.market.application.service.catalog.product;

import Anor.market.application.dto.catalog.product.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.dto.ProductDTO;
import Anor.market.application.mapper.catalog.product.ProductMapper;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.repository.catalog.catalog.CategoryItemListRepository;
import Anor.market.domain.repository.catalog.product.ProductRepository;
import Anor.market.domain.service.catalog.product.ProductService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductServiceImpl implements ProductService that the seller can create and sell his/her own product in anor market
 * all data these come from other DATABASE such as a catalog, category and category item list, once accepted above data with category item list ID
 * seller can announce an ads in this case!
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryItemListRepository categoryItemListRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductMapper productMapper;


    /// CREATE A PRODUCT
    @Override
    public ProductDTO createProduct(String categoryItemListId, ProductCreateDTO createDTO) {
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User id is not found!"));
        boolean isSeller = userEntity.isSeller();
        if (!isSeller && SpringSecurityValid.hasRole(Roles.USER)) {
            throw new AppBadException("You are not a seller!");
        }
        CategoryItemListEntity categoryItemList = categoryItemListRepository.findById(categoryItemListId).orElseThrow(() -> new RuntimeException("CategoryItemList not found"));
        ProductEntity productEntity = productMapper.toProductEntity(createDTO);
        productEntity.setCategoryItemListEntity(categoryItemList);
        productRepository.save(productEntity);
        return productMapper.toProductDTO(productEntity);
    }


    /// GET ALL PRODUCT DATA IN PAGEABLE LIST
    @Override
    public Page<ProductDTO> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("localDateTime").descending());
        Page<ProductEntity> productEntityPage = productRepository.findByOrderByLocalDateTimeDesc(pageRequest);
        List<ProductDTO> productDTOList = productEntityPage.getContent().stream().map(productMapper::toProductDTO).collect(Collectors.toList());
        return new PageImpl<>(productDTOList, pageRequest, productEntityPage.getTotalElements());
    }

    /// UPDATE PRODUCT BY ID
    @Override
    public ProductDTO updateProduct(String productId, ProductCreateDTO createDTO) {
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        boolean isSeller = user.isSeller();
        if (!isSeller) {
            throw new AppBadException("you are not seller!");
        }
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new AppBadException("Product id is not found!"));
        ProductEntity productEntity = productMapper.toUpdateProductEntity(product.getProductId(), createDTO);
        productRepository.save(productEntity);
        return productMapper.toProductDTO(productEntity);
    }

    /// DELETE PRODUCT BY ID
    @Override
    public String deleteProduct(String productId) {
        productRepository.deleteById(productId);
        return "Deleted!";
    }
}

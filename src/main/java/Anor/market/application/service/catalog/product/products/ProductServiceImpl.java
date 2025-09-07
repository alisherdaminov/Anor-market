package Anor.market.application.service.catalog.product.products;

import Anor.market.application.dto.catalog.product.products.create.ProductCreateDTO;
import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import Anor.market.application.mapper.catalog.product.products.ProductMapper;
import Anor.market.application.service.catalog.product.images.ImageServiceImpl;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.repository.catalog.catalog.CategoryItemListRepository;
import Anor.market.domain.repository.catalog.product.products.ProductRepository;
import Anor.market.domain.service.catalog.product.products.ProductService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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
    @Autowired
    private ImageServiceImpl imageService;


    /// CREATE A PRODUCT AND IMAGES
    @Override
    public ProductDTO createProduct(ProductCreateDTO createDTO, List<MultipartFile> files) {
        //finding the current user status!
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User id is not found!"));
        boolean isSeller = userEntity.isSeller();

        // checking is the seller or not
        if (!isSeller && SpringSecurityValid.hasRole(Roles.USER)) {
            throw new AppBadException("You are not a seller!");
        }
        // UUID id = UUID.fromString(categoryItemListIdString);
        //calling by id of the category item list and setting data in product
        CategoryItemListEntity categoryItemList = categoryItemListRepository.findByStringId(createDTO.getCategoryItemListId()).orElseThrow(() -> new RuntimeException("CategoryItemList not found"));

        //creation of Product for sale
        ProductEntity productEntity = productMapper.toProductEntity(createDTO);
        productEntity.setCategoryItemListEntity(categoryItemList); // <---------> PARENT LINK
        productEntity.setSellerName(userEntity.getFirstName());

        LocalDate deliveryDate = LocalDate.now().plusDays(3); // "2025-08-08"
        if (LocalDate.now().isAfter(deliveryDate)) {
            throw new IllegalArgumentException("Delivery date cannot be in the past");
        }
        productEntity.setDeliveryDate(deliveryDate);
        //saved in the DATABASE
        productRepository.save(productEntity);
        //images saved by using imageService save Images
        imageService.saveImages(productEntity.getProductId(), files);
        //to dto
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

    /// GET PRODUCT WITH IMAGES BY ID OF THE PRODUCT AND IMAGE
    @Override
    public ResponseEntity<Resource> getProductWithImage(String productId, String imageId) throws IOException {
        return imageService.getImage(productId, imageId);
    }

    /// UPDATE PRODUCT BY ID
    @Override
    public ProductDTO updateProduct(String productId, ProductCreateDTO createDTO) {
        //finding the current user status!
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        boolean isSeller = user.isSeller();
        // checking is the seller or not
        if (!isSeller) {
            throw new AppBadException("You are not seller!");
        }
        LocalDate deliveryDate = LocalDate.now().plusDays(3); // "2025-08-08"
        if (LocalDate.now().isAfter(deliveryDate)) {
            throw new IllegalArgumentException("Delivery date cannot be in the past");
        }
        ProductEntity product = productRepository.findByStringId(productId).orElseThrow(() -> new AppBadException("Product id is not found!"));
        //update of Product for sale
        ProductEntity productEntity = productMapper.toUpdateProductEntity(product.getProductId(), createDTO);
        productEntity.setDeliveryDate(deliveryDate);
        //saved in the DATABASE
        productRepository.save(productEntity);
        //to dto
        return productMapper.toProductDTO(productEntity);
    }

    /// DELETE PRODUCT BY ID
    @Override
    public String deleteProduct(String productId) {
        productRepository.deleteByProductId(productId);
        return "Deleted!";
    }

    /// DELETE IMAGES BY ID OF THE PRODUCT AND IMAGE
    @Override
    public void removeImage(String productId, String imageId) {
        imageService.removeImage(productId, imageId);
    }
}

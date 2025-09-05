package Anor.market.application.service.catalog.catalog;

import Anor.market.application.dto.catalog.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.catalog.dto.CatalogDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryItemListDTO;
import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import Anor.market.application.mapper.catalog.catalog.CatalogMapper;
import Anor.market.application.mapper.catalog.catalog.CatalogUpdateMapper;
import Anor.market.application.mapper.catalog.product.products.ProductMapper;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.catalog.CatalogEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryEntity;
import Anor.market.domain.model.entity.catalog.catalog.CategoryItemListEntity;
import Anor.market.domain.model.entity.catalog.product.products.ProductEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.repository.catalog.catalog.CatalogRepository;
import Anor.market.domain.repository.catalog.catalog.CategoryItemListRepository;
import Anor.market.domain.repository.catalog.catalog.CategoryRepository;
import Anor.market.domain.repository.catalog.product.products.ProductRepository;
import Anor.market.domain.service.catalog.catalog.CatalogService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.enums.Roles;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CatalogServiceImpl implements CatalogService which has four override methods these are for creating, getting,
 * updating, deleting that catalog, category, category item list implementing by ADMIN
 * And also catalog, category, category item list and product list can be deleted by ADMIN
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryItemListRepository categoryItemListRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CatalogMapper catalogMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CatalogUpdateMapper catalogUpdateMapper;


    /// CREATE A CATALOG
    @Override
    public CatalogDTO createCatalog(CatalogCreateDTO createDTO) {
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User id is not found!"));
        boolean isSeller = userEntity.isSeller();
        if (!isSeller && SpringSecurityValid.hasRole(Roles.USER)) {
            throw new AppBadException("You are not a seller!");
        }

        /// CATALOG ENTITY
        CatalogEntity catalogEntity = catalogMapper.toCatalogEntity(createDTO);
        /// save catalog
        catalogRepository.save(catalogEntity);

        ///  CATEGORY ENTITY LIST
        List<CategoryEntity> categories = createDTO.getCategoryCreateList().stream().map(categoryDTO -> {
            CategoryEntity categoryEntity = catalogMapper.toCategoryEntity(categoryDTO);
            categoryEntity.setCatalogEntity(catalogEntity);// ---- PARENT LINK
            /// save category
            categoryRepository.save(categoryEntity);

            /// CATEGORYITEMLIST ENTITY LIST
            List<CategoryItemListEntity> itemEntities = categoryDTO.getCategoryItemListCreateList().stream().map(itemDTO -> {
                CategoryItemListEntity itemEntity = catalogMapper.toCategoryItemListEntity(itemDTO);
                itemEntity.setCategoryEntity(categoryEntity);// ---- PARENT LINK
                /// save categoryItemList
                categoryItemListRepository.save(itemEntity);
                return itemEntity;
            }).collect(Collectors.toList());

            categoryEntity.setCategoryItemListEntityList(itemEntities);
            return categoryEntity;
        }).collect(Collectors.toList());

        catalogEntity.setCategoryEntityList(categories);
        /// to dto
        return catalogMapper.toCatalogDTO(catalogEntity);
    }

    /// GET ALL THE CATALOG LIST
    @Override
    public Page<CatalogDTO> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<CatalogEntity> catalogEntityList = catalogRepository.findByOrderByCreatedAtDesc(pageRequest);
        List<CatalogDTO> catalogDTOList = catalogEntityList.getContent().stream().map(catalogMapper::toCatalogDTO).collect(Collectors.toList());
        return new PageImpl<>(catalogDTOList, pageRequest, catalogEntityList.getTotalElements());
    }

    /// GET CATEGORY BY ID
    @Override
    public CategoryDTO getCategoryById(String categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppBadException("Category id is not found!"));
        return catalogMapper.toCategoryDTO(category);
    }

    /// GET CATEGORY ITEM LIST BY ID
    @Override
    public CategoryItemListDTO getCategoryItemListById(String categoryItemListId) {
        CategoryItemListEntity categoryItemList = categoryItemListRepository.findById(categoryItemListId).orElseThrow(() -> new AppBadException("Category item list id is not found!"));
        return catalogMapper.toCategoryItemListDTO(categoryItemList);
    }

    /// GET PRODUCT BY ID
    @Override
    public ProductDTO getProductById(String productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new AppBadException("Product id is not found!"));
        return productMapper.toProductDTO(product);
    }

    /// UPDATE ALL THE CATALOG LIST BY CATALOG ID
    @Override
    public CatalogDTO updateCatalog(String catalogId, String productId, CatalogCreateDTO createDTO) {

        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User id is not found!"));
        boolean isSeller = userEntity.isSeller();
        if (!isSeller && SpringSecurityValid.hasRole(Roles.USER)) {
            throw new AppBadException("You are not a seller!");
        }

        /// CATALOG ENTITY DATABASE SEARCH BY ID
        CatalogEntity catalog = catalogRepository.findById(catalogId).orElseThrow(() -> new AppBadException("Catalog id is not found!"));
        /// PRODUCT ENTITY DATABASE SEARCH BY ID
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new AppBadException("Product id is not found!"));

        /// CATALOG ENTITY
        CatalogEntity catalogEntity = catalogUpdateMapper.toUpdateCatalogEntity(catalog.getCatalogId(), product.getProductId(), createDTO);
        catalogEntity.setCreatedAt(LocalDateTime.now());

        ///  CATEGORY ENTITY LIST
        List<CategoryEntity> categoryEntityList = createDTO.getCategoryCreateList().stream().map(categoryCreateDTO -> {
            CategoryEntity category = catalogUpdateMapper.toUpdateCategoryEntity(product.getProductId(), categoryCreateDTO);
            category.setCatalogEntity(catalogEntity);// ---- PARENT LINK

            /// CATEGORYITEMLIST ENTITY LIST
            List<CategoryItemListEntity> categoryItemListEntityList = categoryCreateDTO.getCategoryItemListCreateList().stream().map(itemListCreateDTO -> {
                CategoryItemListEntity itemEntity = catalogUpdateMapper.toUpdateCategoryItemListEntity(product.getProductId(), itemListCreateDTO);
                itemEntity.setCategoryEntity(category);// ---- PARENT LINK
                /// save categoryItemList
                categoryItemListRepository.save(itemEntity);
                return itemEntity;
            }).collect(Collectors.toList());

            category.setCategoryItemListEntityList(categoryItemListEntityList);
            /// save category
            categoryRepository.save(category);
            return category;
        }).collect(Collectors.toList());

        catalogEntity.setCategoryEntityList(categoryEntityList);
        /// save catalog
        catalogRepository.save(catalogEntity);

        /// to dto
        return catalogMapper.toCatalogDTO(catalogEntity);
    }

    ///  DELETE THE CATALOG BY USING CATALOG ID
    @Override
    public String deleteCatalog(String catalogId) {
        catalogRepository.deleteById(catalogId);
        return "Deleted!";
    }

    ///  DELETE THE CATEGORY BY USING CATEGORY ID
    @Override
    public String deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Deleted!";
    }

    ///  DELETE THE CATEGORY ITEM LIST BY USING CATEGORY ITEM LIST ID
    @Override
    public String deleteCategoryItemList(String categoryItemListId) {
        categoryItemListRepository.deleteById(categoryItemListId);
        return "Deleted!";
    }

    ///  DELETE THE PRODUCT BY USING PRODUCT ID
    @Override
    public String deleteProduct(String productId) {
        productRepository.deleteById(productId);
        return "Deleted!";
    }
}

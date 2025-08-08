package Anor.market.application.service.catalog;

import Anor.market.application.dto.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.dto.CatalogDTO;
import Anor.market.application.mapper.catalog.CatalogMapper;
import Anor.market.application.mapper.catalog.ProductMapper;
import Anor.market.domain.model.entity.catalog.CatalogEntity;
import Anor.market.domain.model.entity.catalog.CategoryEntity;
import Anor.market.domain.model.entity.catalog.CategoryItemListEntity;
import Anor.market.domain.model.entity.catalog.ProductEntity;
import Anor.market.domain.repository.catalog.CatalogRepository;
import Anor.market.domain.repository.catalog.CategoryItemListRepository;
import Anor.market.domain.repository.catalog.CategoryRepository;
import Anor.market.domain.repository.catalog.ProductRepository;
import Anor.market.domain.service.catalog.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CatalogServiceImpl implements CatalogService which has four override methods these are for creating, getting,
 * updating, deleting that catalog, category, category item list implementing by ADMIN
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
    private CatalogMapper catalogMapper;
    @Autowired
    private ProductMapper productMapper;


    /// CREATE A CATALOG
    @Override
    public CatalogDTO createCatalog(CatalogCreateDTO createDTO) {
        /// CATALOG ENTITY
        CatalogEntity catalogEntity = catalogMapper.toCatalogEntity(createDTO);

        ///  CATEGORY ENTITY LIST
        List<CategoryEntity> categories = createDTO.getCategoryCreateList().stream().map(categoryDTO -> {
            CategoryEntity categoryEntity = catalogMapper.toCategoryEntity(categoryDTO);
            categoryEntity.setCatalogEntity(catalogEntity);// ---- PARENT LINK

            /// CATEGORYITEMLIST ENTITY LIST
            List<CategoryItemListEntity> itemEntities = categoryDTO.getCategoryItemListCreateList().stream().map(itemDTO -> {
                CategoryItemListEntity itemEntity = catalogMapper.toCategoryItemListEntity(itemDTO);
                itemEntity.setCategoryEntity(categoryEntity);// ---- PARENT LINK

                /// PRODUCT ENTITY LIST
                List<ProductEntity> productEntityList = itemDTO.getProductList().stream().map(productDTO -> {
                    ProductEntity product = productMapper.toProductEntity(productDTO);
                    product.setCategoryItemListEntity(itemEntity);// ---- PARENT LINK
                    /// save products
                    productRepository.save(product);
                    return product;
                }).toList();

                itemEntity.setProductEntityList(productEntityList);
                /// save categoryItemList
                categoryItemListRepository.save(itemEntity);
                return itemEntity;
            }).collect(Collectors.toList());

            categoryEntity.setCategoryItemListEntityList(itemEntities);
            /// save category
            categoryRepository.save(categoryEntity);
            return categoryEntity;
        }).collect(Collectors.toList());

        catalogEntity.setCategoryEntityList(categories);
        /// save catalog
        catalogRepository.save(catalogEntity);
        /// to dto
        return catalogMapper.toCatalogDTO(catalogEntity);
    }


    /// GET ALL THE CATALOG LIST
    @Override
    public List<CatalogDTO> getAll() {
        return List.of();
    }

    /// UPDATE ALL THE CATALOG LIST BY CATALOG ID
    @Override
    public CatalogDTO updateCatalog(String catalogId, CatalogCreateDTO createDTO) {
        return null;
    }

    ///  DELETE THE CATALOG BY USING CATALOG ID
    @Override
    public String deleteCatalog(String catalogId) {
        return "";
    }
}

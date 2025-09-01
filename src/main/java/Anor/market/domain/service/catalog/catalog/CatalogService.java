package Anor.market.domain.service.catalog.catalog;

import Anor.market.application.dto.catalog.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.catalog.dto.CatalogDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryDTO;
import Anor.market.application.dto.catalog.catalog.dto.CategoryItemListDTO;
import Anor.market.application.dto.catalog.product.products.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface CatalogService {

    CatalogDTO createCatalog(CatalogCreateDTO createDTO);

    Page<CatalogDTO> getAll(int page, int size);

    CategoryDTO getCategoryById(String categoryId);

    CategoryItemListDTO getCategoryItemListById(String categoryItemListId);

    ProductDTO getProductById(String productId);

    CatalogDTO updateCatalog(String catalogId, String productId, CatalogCreateDTO createDTO);

    String deleteCatalog(String catalogId);

    String deleteCategory(String categoryId);

    String deleteCategoryItemList(String categoryItemListId);

    String deleteProduct(String productId);
}

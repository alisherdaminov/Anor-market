package Anor.market.domain.service.catalog;

import Anor.market.application.dto.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.dto.CatalogDTO;
import Anor.market.application.dto.catalog.dto.CategoryDTO;
import Anor.market.application.dto.catalog.dto.CategoryItemListDTO;
import Anor.market.application.dto.catalog.dto.ProductDTO;
import org.springframework.data.domain.PageImpl;

public interface CatalogService {

    CatalogDTO createCatalog(CatalogCreateDTO createDTO);

    PageImpl<CatalogDTO> getAll(int page, int size);

    CategoryDTO getCategoryById(String categoryId);

    CategoryItemListDTO getCategoryItemListById(String categoryItemListId);

    ProductDTO getProductById(String productId);

    CatalogDTO updateCatalog(String catalogId, String productId, CatalogCreateDTO createDTO);

    String deleteCatalog(String catalogId);

    String deleteCategory(String categoryId);

    String deleteCategoryItemList(String categoryItemListId);

    String deleteProduct(String productId);
}

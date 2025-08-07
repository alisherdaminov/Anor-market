package Anor.market.domain.service.catalog;

import Anor.market.application.dto.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.dto.CatalogDTO;

import java.util.List;

public interface CatalogService {

    CatalogDTO createCatalog(CatalogCreateDTO createDTO);

    List<CatalogDTO> getAll();

    CatalogDTO updateCatalog(String catalogId, CatalogCreateDTO createDTO);

    String deleteCatalog(String catalogId);
}

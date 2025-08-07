package Anor.market.application.service.catalog;

import Anor.market.application.dto.catalog.create.CatalogCreateDTO;
import Anor.market.application.dto.catalog.dto.CatalogDTO;
import Anor.market.domain.service.catalog.CatalogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {



    @Override
    public CatalogDTO createCatalog(CatalogCreateDTO createDTO) {
        return null;
    }

    @Override
    public List<CatalogDTO> getAll() {
        return List.of();
    }

    @Override
    public CatalogDTO updateCatalog(String catalogId, CatalogCreateDTO createDTO) {
        return null;
    }

    @Override
    public String deleteCatalog(String catalogId) {
        return "";
    }
}

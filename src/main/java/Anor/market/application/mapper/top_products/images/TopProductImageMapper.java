package Anor.market.application.mapper.top_products.images;

import Anor.market.application.dto.top_products.images.TopProductImageDTO;
import Anor.market.domain.model.top_products.images.TopProductImageEntity;
import org.springframework.stereotype.Component;

@Component
public class TopProductImageMapper {

    public TopProductImageDTO toDTO(TopProductImageEntity entity, String baseUrl) {
        if (entity == null) {
            return null;
        }
        return TopProductImageDTO.builder()
                .imageId(entity.getTopProductImageId())
                .origenName(entity.getOrigenName())
                .extension(entity.getExtension())
                .path(entity.getPath())
                .size(entity.getSizes())
                .url(entity.getUrl() != null ? entity.getUrl()
                        : baseUrl + "/" + entity.getPath() + "/" + entity.getTopProductImageId() + "." + entity.getExtension())
                .createdAt(entity.getCreatedAt())
                .build();
    }


}

package Anor.market.application.mapper.home;

import Anor.market.application.dto.home.create.HomeCreateDTO;
import Anor.market.application.dto.home.dto.HomeDTO;
import Anor.market.application.mapper.home.home_product.HomeProductsMapper;
import Anor.market.domain.model.home.HomeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HomeMapper {

    @Autowired
    private HomeProductsMapper homeProductsMapper;

    /// ENTITY TO DTO
    public HomeEntity toEntity(HomeCreateDTO createDTO) {
        return HomeEntity.builder()
                .homeTitle(createDTO.getHomeTitle())
                .createdAt(LocalDateTime.now())
                .build();
    }

    /// DTO TO ENTITY
    public HomeDTO toDTO(HomeEntity homeEntity) {
        return HomeDTO.builder()
                .homeId(homeEntity.getHomeId())
                .homeTitle(homeEntity.getHomeTitle())
                .build();
    }
}

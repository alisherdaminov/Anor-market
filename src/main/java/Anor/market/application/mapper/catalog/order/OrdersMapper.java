package Anor.market.application.mapper.catalog.order;

import Anor.market.application.dto.catalog.order.orders.create.OrdersCreateDTO;
import Anor.market.application.dto.catalog.order.orders.dto.OrdersDTO;
import Anor.market.domain.model.entity.catalog.order.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrdersMapper {

    @Autowired
    private BranchesMapper branchesMapper;
    @Autowired
    private CardsMapper cardsMapper;

    /// DTO TO ENTITY
    public OrdersEntity toEntity(OrdersCreateDTO createDTO) {
        return OrdersEntity.builder()
                .productOfOrdersId(createDTO.getProductId())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public OrdersDTO toDTO(OrdersEntity entity) {
        return OrdersDTO.builder()
                .ordersId(entity.getOrdersId())
                .branchTitle(entity.getBranchTitle())
                .consumerName(entity.getConsumerName())
                .consumerPhoneNumber(entity.getConsumerPhoneNumber())
                .deliveryTitle(entity.getDeliveryTitle())
                .branchName(entity.getBranchName())
                .cardName(entity.getCardName())
                .productPrice(entity.getProductPrice())
                .discountProductPrice(entity.getDiscountProductPrice())
                .overallPrice(entity.getOverallPrice())
                .branches(entity.getBranchesEntity() != null
                        ? branchesMapper.toDTO(entity.getBranchesEntity())
                        : null)
                .cards(entity.getCardsEntity() != null
                        ? cardsMapper.toDTO(entity.getCardsEntity())
                        : null)
                .localDateTime(entity.getLocalDateTime())
                .build();
    }

}

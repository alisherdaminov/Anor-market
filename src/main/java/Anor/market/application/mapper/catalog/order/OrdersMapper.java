package Anor.market.application.mapper.catalog.order;

import Anor.market.application.dto.catalog.order.orders.create.OrdersCreateDTO;
import Anor.market.application.dto.catalog.order.orders.dto.OrdersDTO;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.order.OrdersEntity;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.repository.catalog.product.ProductRepository;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrdersMapper {

    @Autowired
    private BranchesMapper branchesMapper;
    @Autowired
    private CardsMapper cardsMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    /// DTO TO ENTITY
    public OrdersEntity toEntity(OrdersCreateDTO createDTO) {
        return OrdersEntity.builder()
                .branchTitle(createDTO.getBranchTitle())
                .deliveryPrice(createDTO.getDeliveryPrice())
                .branchesEntity(branchesMapper.toEntity(createDTO.getBranchesCreate()))
                .cardsEntity(cardsMapper.toEntity(createDTO.getCardsCreate()))
                .localDateTime(LocalDateTime.now())
                .build();
    }

    /// ENTITY TO DTO
    public OrdersDTO toDTO(OrdersEntity entity, String productId) {
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new AppBadException("Product id is not found!"));

        return OrdersDTO.builder()
                .branchTitle(entity.getBranchTitle())
                .consumerName(user.getFirstName())
                .consumerPhoneNumber(user.getPhoneNumber())
                .deliveryTitle(product.getDeliveryTitle())
                .productPrice(product.getPrice())
                .deliveryPrice(entity.getDeliveryPrice())
                .discountProductPrice(product.getDiscountPriceWithCard())
                .branches(branchesMapper.toDTO(entity.getBranchesEntity()))
                .cards(cardsMapper.toDTO(entity.getCardsEntity()))
                .localDateTime(entity.getLocalDateTime())
                .build();
    }

    /// ENTITY TO DTO
    public OrdersDTO toDtoAll(OrdersEntity entity) {
        return OrdersDTO.builder()
                .branchTitle(entity.getBranchTitle())
                .consumerName(entity.getConsumerName())
                .consumerPhoneNumber(entity.getConsumerPhoneNumber())
                .deliveryTitle(entity.getDeliveryTitle())
                .productPrice(entity.getProductPrice())
                .deliveryPrice(entity.getDeliveryPrice())
                .discountProductPrice(entity.getDiscountProductPrice())
                .branches(branchesMapper.toDTO(entity.getBranchesEntity()))
                .cards(cardsMapper.toDTO(entity.getCardsEntity()))
                .localDateTime(entity.getLocalDateTime())
                .build();
    }

}

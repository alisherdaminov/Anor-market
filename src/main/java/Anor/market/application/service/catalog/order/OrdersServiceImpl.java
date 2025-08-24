package Anor.market.application.service.catalog.order;

import Anor.market.application.dto.catalog.order.orders.create.OrdersCreateDTO;
import Anor.market.application.dto.catalog.order.orders.dto.OrdersDTO;
import Anor.market.application.mapper.catalog.order.OrdersMapper;
import Anor.market.domain.model.entity.auth.UserEntity;
import Anor.market.domain.model.entity.catalog.order.BranchesEntity;
import Anor.market.domain.model.entity.catalog.order.CardsEntity;
import Anor.market.domain.model.entity.catalog.order.OrdersEntity;
import Anor.market.domain.model.entity.catalog.product.ProductEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.repository.catalog.order.BranchesRepository;
import Anor.market.domain.repository.catalog.order.CardsRepository;
import Anor.market.domain.repository.catalog.order.OrdersRepository;
import Anor.market.domain.repository.catalog.product.ProductRepository;
import Anor.market.domain.service.catalog.order.OrdersService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * this is that OrdersServiceImpl implements OrdersService some functions these are creating order,getting all data and delete
 * by id of the product once order is no need to buy, all functions are for only user who can menage their orders
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BranchesRepository branchesRepository;
    @Autowired
    private CardsRepository cardsRepository;


    /// CREATE AN ORDER
    @Override
    public OrdersDTO createOrder(OrdersCreateDTO createDTO) {

        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User is not found!"));
        ProductEntity product = productRepository.findById(createDTO.getProductId()).orElseThrow(() -> new AppBadException("Product id is not found!"));
        BranchesEntity branches = branchesRepository.findById(createDTO.getBranchId()).orElseThrow(() -> new AppBadException("Branch id is not found!"));
        CardsEntity cards = cardsRepository.findById(createDTO.getCardId()).orElseThrow(() -> new AppBadException("Card id is not found!"));
        int overallPrice = product.getPrice() - product.getDiscountPriceWithCard();

        // Orders to entity creations
        OrdersEntity orders = ordersMapper.toEntity(createDTO);
        orders.setUserEntityOrders(user); // <-----> PARENT LINK
        orders.setProductEntityOrders(product);// <-----> PARENT LINK
        orders.setBranchesEntity(branches);// <-----> PARENT LINK
        orders.setCardsEntity(cards);// <-----> PARENT LINK

        //setting other data coming from above databases
        orders.setBranchTitle(branches.getBranchTitle());
        orders.setConsumerName(user.getFirstName());
        orders.setConsumerPhoneNumber(user.getPhoneNumber());
        orders.setDeliveryTitle(product.getDeliveryTitle());
        orders.setBranchName(branches.getCityName());
        orders.setCardName(cards.getCardName());
        orders.setProductPrice(product.getPrice());
        orders.setDiscountProductPrice(product.getDiscountPriceWithCard());
        orders.setOverallPrice(overallPrice);
        //Saved in DATABASE
        ordersRepository.save(orders);
        // to dto
        return ordersMapper.toDTO(orders);
    }

    /// GET ALL ORDERS DATA
    @Override
    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll(Sort.by(Sort.Direction.DESC, "localDateTime")).stream().map(ordersMapper::toDTO).toList();
    }

    /// DELETE ORDERS BY ID
    @Override
    public String deleteOrderById(String ordersId) {
        ordersRepository.deleteById(ordersId);
        return "Deleted!";
    }
}

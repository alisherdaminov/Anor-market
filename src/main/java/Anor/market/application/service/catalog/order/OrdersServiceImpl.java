package Anor.market.application.service.catalog.order;

import Anor.market.application.dto.catalog.order.orders.create.OrdersCreateDTO;
import Anor.market.application.dto.catalog.order.orders.dto.OrdersDTO;
import Anor.market.application.mapper.catalog.order.OrdersMapper;
import Anor.market.domain.model.entity.catalog.order.OrdersEntity;
import Anor.market.domain.repository.catalog.order.OrdersRepository;
import Anor.market.domain.service.catalog.order.OrdersService;
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


    /// CREATE A ORDER
    @Override
    public OrdersDTO createOrder(OrdersCreateDTO createDTO, String productId) {
        OrdersEntity orders = ordersMapper.toEntity(createDTO);
        ordersRepository.save(orders);
        return ordersMapper.toDTO(orders, productId);
    }

    /// GET ALL ORDERS DATA
    @Override
    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll(Sort.by(Sort.Direction.DESC, "localDateTime")).stream().map(ordersMapper::toDtoAll).toList();
    }

    /// DELETE ORDERS BY ID
    @Override
    public String deleteOrderById(String ordersId) {
        ordersRepository.deleteById(ordersId);
        return "Deleted!";
    }
}

package Anor.market.domain.service.catalog.order;

import Anor.market.application.dto.catalog.order.orders.create.OrdersCreateDTO;
import Anor.market.application.dto.catalog.order.orders.dto.OrdersDTO;

import java.util.List;

public interface OrdersService {

    OrdersDTO createOrder(OrdersCreateDTO createDTO, String productId);

    List<OrdersDTO> getAllOrders();

    String deleteOrderById(String ordersId);
}

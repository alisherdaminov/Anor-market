package Anor.market.presentation.controller.catalog.order;

import Anor.market.application.dto.catalog.order.orders.create.OrdersCreateDTO;
import Anor.market.application.dto.catalog.order.orders.dto.OrdersDTO;
import Anor.market.application.service.catalog.order.OrdersServiceImpl;
import Anor.market.presentation.response.AppResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class Orders {

    @Autowired
    private OrdersServiceImpl ordersService;

    @PostMapping("/{productId}")
    public ResponseEntity<AppResponse<OrdersDTO>> createOrder(
            @Valid
            @PathVariable("productId") String productId,
            @RequestBody OrdersCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(ordersService.createOrder(createDTO, productId), "success", new Date()));
    }

    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<OrdersDTO>>> getAllOrders() {
        return ResponseEntity.ok().body(new AppResponse<>(ordersService.getAllOrders(), "success", new Date()));
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<AppResponse<String>> deleteOrderById(
            @Valid
            @PathVariable("ordersId") String ordersId) {
        return ResponseEntity.ok().body(new AppResponse<>(ordersService.deleteOrderById(ordersId), "success", new Date()));
    }

}

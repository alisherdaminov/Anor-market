package Anor.market.domain.service.catalog.cart;


import Anor.market.application.dto.catalog.cart.create.CartCreateDTO;
import Anor.market.application.dto.catalog.cart.dto.CartDTO;

import java.util.List;

public interface CartService {

    CartDTO createCart(CartCreateDTO createDTO);

    List<CartDTO> getAllCarts();

    String deleteCart(String cartId);
}

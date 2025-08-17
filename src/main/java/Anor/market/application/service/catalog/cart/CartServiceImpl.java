package Anor.market.application.service.catalog.cart;

import Anor.market.application.dto.catalog.cart.create.CartCreateDTO;
import Anor.market.application.dto.catalog.cart.dto.CartDTO;
import Anor.market.application.mapper.catalog.cart.CartMapper;
import Anor.market.domain.model.entity.catalog.cart.CartEntity;
import Anor.market.domain.repository.catalog.cart.CartRepository;
import Anor.market.domain.service.catalog.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * CartServiceImpl implements CartService and the user can create a cart after selection of the current product in the marketPlace
 * there is also getting all data of production in the cart page where the user is able to delete if the product is disliked
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;

    /// CREATE A CART BEFORE SALES
    @Override
    public CartDTO createCart(CartCreateDTO createDTO) {
        CartEntity cartEntity = cartMapper.toEntity(createDTO);
        cartRepository.save(cartEntity);
        return cartMapper.toDTO(cartEntity);
    }

    /// GET ALL DATA OF THE PRODUCTIONS
    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAllCarts()
                .stream().map(cartMapper::toDTO)
                .sorted(Comparator.comparing(CartDTO::getLocalDateTime))
                .toList();
    }

    /// DELETE BY CART ID
    @Override
    public String deleteCart(String cartId) {
        cartRepository.deleteCartsById(cartId);
        return "Deleted!";
    }
}

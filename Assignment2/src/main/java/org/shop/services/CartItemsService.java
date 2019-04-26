package org.shop.services;

import java.util.List;

import org.shop.entities.CartItems;
import org.shop.repositories.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemsService {

	@Autowired
	private CartItemsRepository cartItemsRepository;

	public List<CartItems> findByCartId(int cartId) {
		return cartItemsRepository.findByShoppingCartCartId(cartId);
	}

	public CartItems findByItemId(int itemId) {
		return cartItemsRepository.findByItemItemId(itemId);
	}

	public void saveCartItems(CartItems cartItems) {
		cartItemsRepository.save(cartItems);

	}

	public void emptyCart(List<CartItems> list) {
		cartItemsRepository.delete(list);
	}
}
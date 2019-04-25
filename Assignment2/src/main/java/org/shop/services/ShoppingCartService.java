package org.shop.services;

import org.shop.entities.ShoppingCart;
import org.shop.entities.User;
import org.shop.repositories.ShoppingCartRepository;
import org.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	

	public void saveShoppingCart(ShoppingCart shoppingCart) {
		
		shoppingCartRepository.save(shoppingCart);
		
	}



	public ShoppingCart findByUserEmail(String email) {
	
		return shoppingCartRepository.findByUserEmail(email);
	}

	
	public ShoppingCart findBycartId(int cartId) {
		
		return shoppingCartRepository.findByCartId(cartId);
	}
}

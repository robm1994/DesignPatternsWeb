package org.shop.repositories;


import org.shop.entities.CartItems;
import org.shop.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

	CartItems findByShoppingCartCartId(int cartId);

	CartItems findByItemItemId(int itemId);

	

}

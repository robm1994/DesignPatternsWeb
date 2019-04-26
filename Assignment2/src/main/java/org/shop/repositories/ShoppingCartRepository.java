package org.shop.repositories;

import org.shop.entities.ShoppingCart;
import org.shop.entities.User;
import org.shop.entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	ShoppingCart findByUserEmail(String email);

	ShoppingCart findByCartId(int cartId);
}

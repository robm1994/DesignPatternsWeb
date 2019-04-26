package org.shop.controllers;

import java.util.ArrayList;

import org.shop.entities.CartItems;
import org.shop.entities.Item;
import org.shop.entities.ShoppingCart;
import org.shop.entities.User;
import org.shop.services.ItemService;
import org.shop.services.ShoppingCartService;
import org.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerOrderController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;


	
	@GetMapping("/makeOrder")
	public String placeOrder(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne (auth.getName());
		ShoppingCart cart = shoppingCartService.findByUserEmail(user.getEmail());
		
		ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
		cart_items.addAll(cart.getCartItems());

		double total = 0;
		for (int i = 0; i < cart_items.size(); i++) {
		CartItems cartItem = cart_items.get(i);
		Item item = itemService.findOne(cartItem.getItem().getItemId());
		total = total + (item.getPrice() * cartItem.getQuantity());
		}
		
		model.addAttribute("cart", cart);
		model.addAttribute("cartItems", cart_items);
		model.addAttribute("total", total);
		
		return "views/order";
		
	}

}


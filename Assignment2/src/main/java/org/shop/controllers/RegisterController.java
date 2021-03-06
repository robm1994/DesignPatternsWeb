package org.shop.controllers;

import javax.validation.Valid;

import org.shop.entities.ShoppingCart;
import org.shop.entities.User;
import org.shop.services.ShoppingCartService;
import org.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping("/register")
	public String registerForm(Model model) {

		model.addAttribute("user", new User());
		return "views/registerForm";
	}

	@PostMapping("/register")
	public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "views/registerForm";
		}
		if (userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist", true);

			return "views/registerForm";

		}
		userService.createUser(user);
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		shoppingCartService.saveShoppingCart(shoppingCart);

		return "views/success";
	}

}

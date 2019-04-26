package org.shop.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.shop.entities.CartItems;
import org.shop.entities.Item;
import org.shop.entities.MasterCard;
import org.shop.entities.PurchaseFacadeImp;
import org.shop.entities.ShoppingCart;
import org.shop.entities.User;
import org.shop.entities.UserOrder;
import org.shop.entities.Visa;
import org.shop.services.CartItemsService;
import org.shop.services.ItemService;
import org.shop.services.OrderService;
import org.shop.services.ShoppingCartService;
import org.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerOrderController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	public PurchaseFacadeImp purchaseFacade;

	@Autowired
	private CartItemsService cartItemsService;

	@GetMapping("/order")
	public String order(Model model, @RequestParam("cartId") int id) {
		ShoppingCart cart = shoppingCartService.findBycartId(id);

		ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
		cart_items.addAll(cart.getCartItems());

		double total = cart.calculateTotal();

		Set<Item> items = new HashSet<>();

		for (int i = 0; i < cart_items.size(); i++) {
			CartItems cartItem = cart_items.get(i);
			Item item = itemService.findOne(cartItem.getItem().getItemId());

			items.add(item);
		}

		model.addAttribute("cart", cart);
		model.addAttribute("items", items);
		model.addAttribute("total", total);

		return "views/order";
	}

	@PostMapping("/order")
	public String order(Model model, @Valid @ModelAttribute("userOrder") UserOrder userOrder,
			@RequestParam("total") double total, HttpServletRequest request) {
		String view = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		Set<Item> items = new HashSet<>();
		ShoppingCart cart = user.getCart();
		ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
		cart_items.addAll(cart.getCartItems());

		purchaseFacade = new PurchaseFacadeImp();

		if (purchaseFacade.placeOrder(cart_items)) {

			items.addAll(items);

			UserOrder order = new UserOrder(user, items, total);

			if (request.getParameter("payment_method").equals("Visa")) {

				Visa visa = new Visa(request.getParameter("name"), request.getParameter("cardNumber"),
						request.getParameter("expires"));

				if (order.pay(visa, cart)) {
					order.setPaymentMethod("Visa");
					order.setAddress(user.getAddress());
					orderService.saveOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(cartItemsService.findByCartId(cart.getCartId()));

					view = "views/visaApproval";
				} else {
					String visaError = "";
					model.addAttribute("visaError", visaError);
					model.addAttribute("total", total);
					view = "views/order";
				}
			} else if (request.getParameter("payment_method").equals("Mastercard")) {

				MasterCard mastercard = new MasterCard(request.getParameter("name"), request.getParameter("cardNumber"),
						request.getParameter("expires"));

				if (order.pay(mastercard, cart)) {
					order.setPaymentMethod("MasterCard");
					orderService.saveOrder(order);
					itemService.updateStock(cart_items);
					cartItemsService.emptyCart(cartItemsService.findByCartId(cart.getCartId()));

					view = "views/masterApproval";
				} else {
					String mastercardError = "";
					model.addAttribute("total", total);
					model.addAttribute("mastercardError", mastercardError);
					view = "views/order";
				}
			}
			return view;

		} else {
			model.addAttribute("total", total);
			return "views/order";
		}
	}

	@GetMapping("/viewOrder")
	public String viewOrder(Model model, @RequestParam("id") int id) {

		UserOrder order = orderService.findById(id);
		model.addAttribute("items", order.getOrderItems());

		return "views/viewOrder";
	}

}

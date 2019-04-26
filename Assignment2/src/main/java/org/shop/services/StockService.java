package org.shop.services;

import java.util.ArrayList;

import org.shop.entities.CartItems;
import org.shop.entities.Item;

public class StockService {

	public StockService() {

	}

	public boolean isAvailable(ArrayList<CartItems> cart_items) {

		boolean available = true;

		for (int i = 0; i < cart_items.size(); i++) {
			CartItems cartItem = cart_items.get(i);
			Item item = cart_items.get(i).getItem();

			if (cartItem.getQuantity() > item.getStock()) {

				available = false;
			}
		}
		return available;
	}
}
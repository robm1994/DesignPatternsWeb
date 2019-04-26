package org.shop.entities;

import java.util.ArrayList;

import org.shop.services.StockService;

public class PurchaseFacadeImp implements PurchaseFacade {

	StockService stockService = new StockService();

	public PurchaseFacadeImp() {

	}

	@Override
	public boolean placeOrder(ArrayList<CartItems> cart_items) {
		boolean fine = false;
		if (stockService.isAvailable(cart_items)) {
			fine = true;
		}
		return fine;
	}

}
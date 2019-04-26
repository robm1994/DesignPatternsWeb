package org.shop.services;

import java.util.ArrayList;

import org.shop.entities.UserOrder;
import org.shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public UserOrder findById(int id) {
		return orderRepository.findById(id);
	}

	public ArrayList<UserOrder> findOrdersByUserEmail(String email) {
		return orderRepository.findOrdersByUserEmail(email);
	}

	public void saveOrder(UserOrder order) {
		orderRepository.save(order);
	}

}

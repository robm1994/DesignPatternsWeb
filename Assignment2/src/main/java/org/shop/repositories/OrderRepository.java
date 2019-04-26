package org.shop.repositories;

import java.util.ArrayList;

import org.shop.entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UserOrder, Integer> {

	UserOrder findById(int id);

	ArrayList<UserOrder> findOrdersByUserEmail(String email);

}
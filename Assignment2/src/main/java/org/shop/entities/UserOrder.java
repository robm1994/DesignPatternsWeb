package org.shop.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class UserOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "USER_EMAIL", updatable = false)
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<Item> orderItems;

	private double total;
	private String paymentMethod;
	private String address;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getItems() {
		return orderItems;
	}

	public void setItems(Set<Item> items) {
		this.orderItems = items;
	}

	public UserOrder(User user, Set<Item> items) {
		this.user = user;
		this.orderItems = items;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Set<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = (Set<Item>) orderItems;
	}

	public UserOrder() {

	}

	public UserOrder(User user, Set<Item> items, double total) {
		this.user = user;
		this.orderItems = items;
		this.total = total;
	}

	public boolean pay(PaymentMethod method, ShoppingCart cart) {
		double total = cart.calculateTotal();
		return method.pay(total);
	}

}

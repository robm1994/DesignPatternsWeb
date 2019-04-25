package org.shop.entities;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Item {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int itemId;
@NotEmpty
private String category;
@NotEmpty
private String manufacturer;
@NotEmpty
private String title;
private int stock;
private int price;

//@ManyToMany(mappedBy = "items")
//private List<ShoppingCart> shoppingCarts;

@OneToMany(mappedBy = "item")
private List<CartItems> cartItems;

@ManyToMany(mappedBy = "orderItems")
private List<UserOrder> userOrders;


public int getItemId() {
	return itemId;
}
	
public void setItemId(int itemId) {
	this.itemId = itemId;
	
}
	
public String getCategory() {
	return category;

}
	public void setCategory(String category) {
	this.category = category;
	}
	public String getManufacturer() {
	return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
	}
	public String getTitle() {
	return title;
	}
	public void setTitle(String title) {
	this.title = title;
	}
	public int getStock() {
	return stock;
	}
	public void setStock(int stock) {
	this.stock = stock;
	}
	public int getPrice() {
	return price;
	}
	public void setPrice(int price) {
	this.price = price;
	}

	
	public Item() {
		
		
	}

	public Item(int itemId, String category, String manufacturer, String title, int stock, int price) {
		
		this.itemId = itemId;
		this.category = category;
		this.manufacturer = manufacturer;
		this.title = title;
		this.stock = stock;
		this.price = price;
		
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public List<UserOrder> getUserOrders() {
		return userOrders;
	}

	public void setUserOrders(List<UserOrder> userOrders) {
		this.userOrders = userOrders;
	}
	

	
	
}
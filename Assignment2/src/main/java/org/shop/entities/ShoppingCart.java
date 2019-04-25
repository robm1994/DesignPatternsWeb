package org.shop.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ShoppingCart {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int cartId;



@OneToMany(mappedBy = "shoppingCart")
private Set<CartItems> cartItems;

@OneToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "user_email", nullable = false)
private User user;

public int getCartId() {
return cartId;
}

public void setCartId(int cartId) {
this.cartId = cartId;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}




public Set<CartItems> getCartItems() {
return cartItems;
}

public void setCartItems(Set<CartItems> cartItems) {
this.cartItems = cartItems;
}


public ShoppingCart(int cartId, User user) {
this.cartId = cartId;
this.user = user;
}

public ShoppingCart() {

}

}
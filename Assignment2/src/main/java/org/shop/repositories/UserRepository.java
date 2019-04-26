package org.shop.repositories;

import java.util.List;

import org.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByNameLike(String name);

	User findOne(String email);

	
	List<User> findByEmail(String email);
	

}

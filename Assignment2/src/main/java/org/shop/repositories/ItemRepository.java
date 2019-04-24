package org.shop.repositories;

import java.util.List;

import org.shop.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

List<Item> findByTitleLike(String title);

}
package org.shop.services;

import java.util.List;

import org.shop.entities.Item;
import org.shop.entities.User;
import org.shop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

@Autowired
private ItemRepository itemRepository;

public void addItem(Item item) {
itemRepository.save(item);
}

public Item findOne(int id) {

 return itemRepository.findOne(id);
}

public List<Item> findByTitle(String title) {
// TODO Auto-generated method stub
return  itemRepository.findByTitleLike("%"+title+"%");
}

public List<Item> findByManufacturer(String manufacturer){
	
	return itemRepository.findByManufacturer("%"+manufacturer+"%");
}

}

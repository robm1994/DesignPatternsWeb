package org.shop.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.shop.entities.Item;
import org.shop.entities.User;
import org.shop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

@Autowired 
private ItemService itemService;

@GetMapping("/addItem")
public String addItemForm(Model model) {

model.addAttribute("item", new Item());
return "views/addItem";
}

@PostMapping("/addItem")
public String addItem(@Valid Item item, BindingResult bindingResult, Model model) {
if(bindingResult.hasErrors()) {
return "views/addItem";
}

itemService.addItem(item);

return "redirect:/addItem";
//return "views/profile";
}


@GetMapping("/items")
public String listItems(Model model, @RequestParam(defaultValue="")  String title) {
model.addAttribute("items", itemService.findByTitle(title));
//model.addAttribute("items", itemService.findByManufacturer(manufacturer));
return "views/itemList";
}

}

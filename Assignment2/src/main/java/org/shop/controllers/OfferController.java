package org.shop.controllers;
//package org.taskfinder.controllers;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.taskfinder.entities.Offer;
//import org.taskfinder.entities.Task;
//import org.taskfinder.entities.User;
//import org.taskfinder.services.OfferService;
//import org.taskfinder.services.TaskService;
//import org.taskfinder.services.UserService;
//
//@Controller
//public class OfferController {
//	
//@Autowired
//private OfferService offerService;
//
//@Autowired
//private TaskService taskService;
//
//@Autowired
//private UserService userService;
//
//@GetMapping("/addOffer")
//public String addOffer(String email, Model model, HttpSession session) {
//	 
//	session.setAttribute("email", email); 
//	 
//	 model.addAttribute("offer", new Offer());
//	 return "views/taskList";
//	 
//}
//
//@PostMapping("/addOffer")
//public String addOffer(@Valid Offer offer,/*Authentication authentication,*/ BindingResult bindingResult, HttpSession session) {
//	 if(bindingResult.hasErrors()) {
//		 return "views/taskList";
//	 }
//	 
//	
//	String email = (String) session.getAttribute("email");
//	//authentication.getPrincipal();
//	//System.out.println("the user email is" + email);
//	
//	//taskService.findById(id);
//	//Long id = (Long) session.getAttribute("id");
//	// offerService.addOffer(offer, userService.findOne(email));
//	
//	 
//	return  "redirect:/profile";
//}
//
//
//
//
//}

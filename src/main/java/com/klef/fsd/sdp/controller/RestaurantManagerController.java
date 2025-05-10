package com.klef.fsd.sdp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsd.sdp.dto.ItemDTO;
import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.Item;
import com.klef.fsd.sdp.model.RestaurantManager;
import com.klef.fsd.sdp.security.JWTUtilities;
import com.klef.fsd.sdp.service.RestaurantManagerService;

@RestController
@RequestMapping("/manager")
@CrossOrigin("*") // * means any URL
public class RestaurantManagerController {
	 @Autowired
	   private RestaurantManagerService managerService;
	 
	 @Autowired
	    JWTUtilities utilities;
		   
	 @PostMapping("/checkmanagerlogin")
	 public ResponseEntity<?> checkmanagerlogin(@RequestBody RestaurantManager manager) {
	     try {
	         RestaurantManager m = managerService.checkmanagerlogin(manager.getUsername(), manager.getPassword());

	         if (m != null) {
	             // Generate JWT token with "MANAGER" role
	             String token = utilities.generateToken(m.getUsername(), "MANAGER");
	             
	             // Return both token and manager data
	             Map<String, Object> response = new HashMap<>();
	             response.put("token", token);
	             response.put("user", m);
	             
	             return ResponseEntity.ok(response);
	         } else {
	             return ResponseEntity.status(401).body("Invalid Username or Password");
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
	     }
	 }
	   
	   @PostMapping("/additem")
	   public ResponseEntity<String> addevent(@RequestBody ItemDTO dto) 
	   {
	       try 
	       {
	           RestaurantManager manager = managerService.getManagerById(dto.manager_id);

	           Item event = new Item();
	           event.setCategory(dto.category);
	           event.setTitle(dto.title);
	           event.setDescription(dto.description);
	           event.setCost(dto.cost); 
	           event.setManager(manager);

	           String output = managerService.addevent(event);
	           return ResponseEntity.ok(output); // 200 - success
	       } 
	       catch (Exception e) 
	       { 
	    	   return ResponseEntity.status(500).body("Failed to Add Event: " + e.getMessage());
	       }
	   }
	   
	   @GetMapping("/vieweventsbymanager/{id}")
	   public ResponseEntity<List<Item>> vieweventsbymanager(@PathVariable int id) 
	   {
	       List<Item> events = managerService.vieweventsbymanager(id);
	       return ResponseEntity.ok(events);
	   }


	   @GetMapping("/viewbookingsbymanager/{managerId}")
	   public ResponseEntity<List<BookItem>> viewBookingsByManager(@PathVariable int managerId) 
	   { 
	       List<BookItem> items = managerService.getbookingsbyManager(managerId);
	       return ResponseEntity.ok(items);
	   }
	   
	   @GetMapping("/updatebookingstatus")
	   public ResponseEntity<String> updateBookingStatus(
	       @RequestParam int id,
	       @RequestParam String status) { 
	       try {
	           String output = managerService.updatebookingstatus(id, status);
	           return ResponseEntity.ok(output);
	       } catch (Exception e) {
	           System.out.println(e.getMessage());
	           return ResponseEntity.status(500).body("Error: " + e.getMessage());
	       }
	   }

	   
}

package com.klef.fsd.sdp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsd.sdp.model.Admin;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.RestaurantManager;
import com.klef.fsd.sdp.security.JWTUtilities;
import com.klef.fsd.sdp.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*") // * means any URL
public class AdminController {
	 @Autowired
	  private AdminService adminService;
	 
	 @Autowired
	    JWTUtilities utilities;

	  
	 @PostMapping("/checkadminlogin")
	 public ResponseEntity<?> checkadminlogin(@RequestBody Admin admin) {
	     try {
	         Admin a = adminService.checkadminlogin(admin.getUsername(), admin.getPassword());
	         
	         if (a != null) {
	             // Generate and return token + user data
	             String token = utilities.generateToken(a.getUsername(), "ADMIN");
	             Map<String, Object> response = new HashMap<>();
	             response.put("token", token);
	             response.put("user", a);
	             return ResponseEntity.ok(response); // 200 OK
	         } else {
	             return ResponseEntity.status(401).body("Invalid credentials"); // 401 Unauthorized
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(500).body("Login error: " + e.getMessage()); // 500 Server Error
	     }
	 }
	 
	 
	  @GetMapping("/viewallcustomers")
	  public ResponseEntity<List<Customer>> viewallcustomers()
	  {
		 List<Customer> customers =  adminService.displaycustomers();
		 
		 return ResponseEntity.ok(customers); // 200 - success
	  }
	  
	  @PostMapping("/addeventmanager")
	  public ResponseEntity<String> addeventmanager(@RequestBody RestaurantManager manager)
	  {
		   try
		   {
			  String output = adminService.addeventmanager(manager);
			  return ResponseEntity.ok(output); // 200 - success
		   }
		   catch(Exception e)
		   {
			   //return ResponseEntity.status(500).body("Failed to Add Event Manager: " + e.getMessage());
			   return ResponseEntity.status(500).body("Failed to Add Event Manager ... !!"); 
		   }
	  }
	  
	  @GetMapping("/viewalleventmanagers")
	  public ResponseEntity<List<RestaurantManager>> viewalleventmanagers()
	  {
		 List<RestaurantManager> eventmanagers =  adminService.displayeventmanagers();
		 
		 return ResponseEntity.ok(eventmanagers); // 200 - success
	  }
	  
	  @DeleteMapping("/deletecustomer")
	  public ResponseEntity<String> deletecustomer(@RequestParam int cid)
	  {
		  try
		   {
			  String output = adminService.deletecustomer(cid);
			  return ResponseEntity.ok(output);
		   }
		   catch(Exception e)
		   {
			    return ResponseEntity.status(500).body("Failed to Delete Customer ... !!"); 
		   }
	  }
	  
	  @GetMapping("/customercount")
	  public ResponseEntity<Long> getCustomerCount()
	  {
	      long count = adminService.displaycustomercount();
	      return ResponseEntity.ok(count);
	  }

	  @GetMapping("/managercount")
	  public ResponseEntity<Long> getManagerCount()
	  {
	      long count = adminService.displaymanagercount();
	      return ResponseEntity.ok(count);
	  }

	  @GetMapping("/eventcount")
	  public ResponseEntity<Long> getEventCount()
	  {
	      long count = adminService.displayeventcount();
	      return ResponseEntity.ok(count);
	  }
	  
	  @GetMapping("/dashboard")
	  public String adminAccess(@RequestHeader("Authorization") String authHeader) {
	      String token = authHeader.substring(7); // Remove "Bearer " prefix
	      Map<String, String> data = utilities.validateToken(token);

	      if (data.get("code").equals("200") && "ADMIN".equals(data.get("role"))) {
	          return "Welcome Admin: " + data.get("username");
	      } else {
	          return "Access Denied!";
	      }
	  }

}

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
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.DeliveryAgent;
import com.klef.fsd.sdp.security.JWTUtilities;
import com.klef.fsd.sdp.service.DeliveryAgentService;

@RestController
@RequestMapping("/agent")
@CrossOrigin("*")
public class DeliveryAgentController {
	
	@Autowired
	private DeliveryAgentService agentservice;
	
	@Autowired
    JWTUtilities utilities;
	 
	   @PostMapping("/registration")
	   public ResponseEntity<String> agentregistration(@RequestBody DeliveryAgent agent)
	   {
		   try
		   {
			  String output = agentservice.agentregistration(agent);
			  return ResponseEntity.ok(output); // 200 - success
		   }
		   catch(Exception e)
		   {
			   return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
//			   return ResponseEntity.status(500).body("Agent Registration Failed ...");
		   }
	   }
	   
	   @PostMapping("/login")
	   public ResponseEntity<?> agentlogin(@RequestBody DeliveryAgent agent) {
	       try {
	           DeliveryAgent c = agentservice.agentlogin(agent.getUsername(), agent.getPassword());

	           if (c != null) {
	               // Generate JWT token with "AGENT" role
	               String token = utilities.generateToken(c.getUsername(), "AGENT");
	               
	               // Return both token and agent data
	               Map<String, Object> response = new HashMap<>();
	               response.put("token", token);
	               response.put("user", c);
	               
	               return ResponseEntity.ok(response);
	           } else {
	               return ResponseEntity.status(401).body("Invalid Username or Password");
	           }
	       } catch (Exception e) {
	           return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
	       }
	   }

	
	   @GetMapping("/viewbookingsbyagent")
	   public ResponseEntity<List<BookItem>> viewBookingsByAgent() 
	   { 
	       List<BookItem> items = agentservice.getbookingsbyAgent();
	       return ResponseEntity.ok(items);
	   }
}

package com.klef.fsd.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.Admin;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.RestaurantManager;
import com.klef.fsd.sdp.repository.AdminRepository;
import com.klef.fsd.sdp.repository.CustomerRepository;
import com.klef.fsd.sdp.repository.ItemRepository;
import com.klef.fsd.sdp.repository.RestaurantManagerRepository;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
    private AdminRepository adminRepository;
	
	@Autowired
    private RestaurantManagerRepository managerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Admin checkadminlogin(String username, String password) 
	{
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addeventmanager(	RestaurantManager manager) 
	{
		managerRepository.save(manager);
		return "Event Manager Added Successfully";
	}

	@Override
	public List<RestaurantManager> displayeventmanagers() 
	{
		return managerRepository.findAll();
	}

	@Override
	public List<Customer> displaycustomers() 
	{
		return customerRepository.findAll();
	}

	@Override
	public String deletecustomer(int cid) 
	{
	    Optional<Customer> customer = customerRepository.findById(cid);
	    
	    if (customer.isPresent()) 
	    {	
	        customerRepository.deleteById(cid);
	        return "Customer Deleted Successfully";
	    } 
	    else 
	    {
	        return "Customer ID Not Found";
	    }
	}
	
	@Override
	public long displaycustomercount() 
	{
		return customerRepository.count();
	}

	@Override
	public long displaymanagercount() 
	{
		return managerRepository.count();
	}

	@Override
	public long displayeventcount() 
	{
		return itemRepository.count();
	}

}

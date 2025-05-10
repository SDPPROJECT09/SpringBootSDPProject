package com.klef.fsd.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.Item;
import com.klef.fsd.sdp.repository.BookItemRepository;
import com.klef.fsd.sdp.repository.CustomerRepository;
import com.klef.fsd.sdp.repository.ItemRepository;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
	private ItemRepository eventRepository;
	
	@Autowired
	private BookItemRepository bookEventRepository;
	
	@Override
	public String customerregistration(Customer customer) 
	{
		customerRepository.save(customer);
		return "Customer Registered Successfully";
	}

	@Override
	public Customer checkcustomerlogin(String username, String password) 
	{
		return customerRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String customerupdateprofile(Customer customer) 
	{
		Optional<Customer> object	= customerRepository.findById(customer.getId());
		
		String msg = null;
			  
			  if(object.isPresent())
			  {
				  Customer c = object.get();
				  
				  c.setName(customer.getName());
				  c.setDob(customer.getDob());
				  c.setMobileno(customer.getMobileno());
				  c.setEmail(customer.getEmail());
				  c.setPassword(customer.getPassword());
				  c.setLocation(customer.getLocation());
				  
				  customerRepository.save(customer);
				  
				  msg = "Customer Profile Updated Successfully";
			  }
			  else
			  {
				  msg = "Customer ID Not Found to Update";
			  }
			  return msg;
	}

	@Override
	public List<Item> viewallevents() 
	{
	   return eventRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int cid) 
	{
		return customerRepository.findById(cid).orElse(null);
	}

	@Override
	public Item getEventById(int eid) 
	{
		return eventRepository.findById(eid).orElse(null);
	}

	@Override
	public String bookevent(BookItem bookItem) 
	{
		bookEventRepository.save(bookItem);
		return "Event Booked Successfully";
	}

	@Override
	public List<BookItem> getbookedeventsByCustomer(int cid) 
	{
		Customer customer = customerRepository.findById(cid).orElse(null);
		return bookEventRepository.findByCustomer(customer);
	}

}
package com.klef.fsd.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.Item;
import com.klef.fsd.sdp.model.RestaurantManager;
import com.klef.fsd.sdp.repository.BookItemRepository;
import com.klef.fsd.sdp.repository.ItemRepository;
import com.klef.fsd.sdp.repository.RestaurantManagerRepository;

@Service
public class RestaurantManagerServiceImpl implements RestaurantManagerService
{
	@Autowired
    private RestaurantManagerRepository managerRepository;
	
	@Autowired
    private ItemRepository eventRepository;
	
	@Autowired
	private BookItemRepository bookEventRepository;
	
	@Override
	public RestaurantManager checkmanagerlogin(String username, String password) 
	{
		return managerRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addevent(Item item) 
	{
		eventRepository.save(item);
		return "Event Added Successfully";
	}

	@Override
	public RestaurantManager getManagerById(int mid) 
	{
	   return managerRepository.findById(mid).get();
	}

	@Override
	public List<Item> vieweventsbymanager(int mid) 
	{
		 RestaurantManager manager = managerRepository.findById(mid).orElse(null);
		 return eventRepository.findByManager(manager);
	}

	@Override
	public List<BookItem> getbookingsbyManager(int mid) 
	{
		return bookEventRepository.getbookingsbyManager(mid);
	}

	@Override
	public String updatebookingstatus(int id, String status) 
	{
		bookEventRepository.updateStatusById(status,id);
		return "Booking Status Updated Successfully";
	}

}
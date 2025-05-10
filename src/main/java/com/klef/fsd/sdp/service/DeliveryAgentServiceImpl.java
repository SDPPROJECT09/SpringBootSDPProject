package com.klef.fsd.sdp.service;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.DeliveryAgent;
import com.klef.fsd.sdp.repository.BookItemRepository;
import com.klef.fsd.sdp.repository.DeliveryAgentRepository;

@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService{
    
	@Autowired
	private DeliveryAgentRepository agentrepository;
	
	@Autowired
	private BookItemRepository bookEventRepository;
	
	@Override
	public String agentregistration(DeliveryAgent agent) {
		// TODO Auto-generated method stub
		agentrepository.save(agent);
		return "Delivery Agent Registered Successfully";
	}

	@Override
	public DeliveryAgent agentlogin(String username, String password) {
		// TODO Auto-generated method stub
		return agentrepository.findByUsernameAndPassword(username, password);
	}
	
	@Override
	public List<BookItem> getbookingsbyAgent() 
	{
		return bookEventRepository.getbookingsbyAgent();
	}
	
	
}

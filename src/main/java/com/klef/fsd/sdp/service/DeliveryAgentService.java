package com.klef.fsd.sdp.service;

import java.util.List;

import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.DeliveryAgent;

public interface DeliveryAgentService {
	public String agentregistration(DeliveryAgent agent);
	public DeliveryAgent agentlogin(String username, String password);
	public List<BookItem> getbookingsbyAgent();
}

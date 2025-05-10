package com.klef.fsd.sdp.service;

import java.util.List;

import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.Item;
import com.klef.fsd.sdp.model.RestaurantManager;

public interface RestaurantManagerService 
{
  public RestaurantManager checkmanagerlogin(String username,String password);
  
  public String addevent(Item event);
  public List<Item> vieweventsbymanager(int mid);
  
  public RestaurantManager getManagerById(int mid);
  
  public List<BookItem> getbookingsbyManager(int mid);
  
  public String updatebookingstatus(int id,String status);
}
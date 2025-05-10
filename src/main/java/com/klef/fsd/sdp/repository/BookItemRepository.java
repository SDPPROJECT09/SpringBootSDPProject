package com.klef.fsd.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.klef.fsd.sdp.model.BookItem;
import com.klef.fsd.sdp.model.Customer;




@Repository
public interface BookItemRepository extends JpaRepository<BookItem,Integer>
{
  public List<BookItem> findByCustomer(Customer customer);
  
  @Query("SELECT b from BookItem b where b.item.manager.id = ?1")
  public List<BookItem> getbookingsbyManager(int mid);
  
  @Query("SELECT b from BookItem b ")
  public List<BookItem> getbookingsbyAgent();
  
  @Modifying
  @Transactional
  @Query("UPDATE BookItem b SET b.status = ?1 WHERE b.id = ?2")
  public int updateStatusById(String status,int id);
  

}
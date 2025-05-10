package com.klef.fsd.sdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.fsd.sdp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Customer findByUsernameAndPassword(String username, String password);
	
	@Query("select count(c) from Customer c")
	public long customercount();
}

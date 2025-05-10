package com.klef.fsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.fsd.sdp.model.RestaurantManager;



@Repository
public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, Integer>{
	public RestaurantManager findByUsernameAndPassword(String username, String password);
	
	@Query("select count(m) from RestaurantManager m")
	public long managercount();
}


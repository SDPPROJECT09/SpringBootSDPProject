package com.klef.fsd.sdp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="manager_table")
public class RestaurantManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manager_id")
    private int id;
    @Column(length = 50,nullable = false)
    private String name;
    @Column(length = 10,nullable = false)
    private String gender;
    @Column(length = 20,nullable = false)
    private String dob;
    @Column(length = 50,nullable = false,unique=true)
    private String email;
    @Column(length = 50,nullable = false,unique=true)
    private String username;
    @Column(length = 50,nullable = false)
    private String password;
    @Column(length = 20,nullable = false,unique=true)
    private String mobileno;
    @Column(length = 100,nullable = false)
    private String restaurant_name;
    @Column(length = 100,nullable = false)
    private String restaurant_location;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getRestaurant_location() {
		return restaurant_location;
	}
	public void setRestaurant_location(String restaurant_location) {
		this.restaurant_location = restaurant_location;
	}

}

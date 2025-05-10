package com.klef.fsd.sdp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="agent_table")
public class DeliveryAgent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="agent_id")
	private int id;
	@Column(name="agent_name", length=50, nullable=false)
	private String name;
	@Column(name="agent_gender", length=50, nullable=false)
	private String gender;
	@Column(name="agent_dob", length=50, nullable=false)
	private String dob;
	@Column(name="agent_mobileno", length=50, nullable=false, unique=true)
	private String mobileno;
	@Column(name="agent_email", length=50, nullable=false, unique=true)
	private String email;
	@Column(name="agent_username", length=50, nullable=false, unique=true)
	private String username;
	@Column(name="agent_password", length=50, nullable=false)
	private String password;
	@Column(name="agent_location", length=50, nullable=false)
	private String location;
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
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "DeliveryAgent [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", mobileno="
				+ mobileno + ", email=" + email + ", username=" + username + ", password=" + password + ", location="
				+ location + "]";
	}
	

}

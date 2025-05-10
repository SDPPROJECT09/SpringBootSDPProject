package com.klef.fsd.sdp.model;

import java.sql.Blob;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_table")
public class Item 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
  private int id;
  @Column(nullable = false,length = 100)
  private String category;
  @Column(nullable = false,length = 100)
  private String title;
  @Column(nullable = false,length = 500)
  private String description;
  @Column(nullable = false)
  private double cost;

  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "manager_id") // Foreign key column
  private RestaurantManager manager;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public double getCost() {
	return cost;
}

public void setCost(double cost) {
	this.cost = cost;
}

public RestaurantManager getManager() {
	return manager;
}

public void setManager(RestaurantManager manager) {
	this.manager = manager;
}

}
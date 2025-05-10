package com.klef.fsd.sdp.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "bookitem_table")
public class BookItem
{
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private String status;

    // Automatically sets the booking time at record creation
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime bookingtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getBookingtime() {
		return bookingtime;
	}

	public void setBookingtime(LocalDateTime bookingtime) {
		this.bookingtime = bookingtime;
	}

	@Override
	public String toString() {
		return "BookItem [id=" + id + ", item=" + item + ", customer=" + customer + ", quantity=" + quantity
				+ ", status=" + status + ", bookingtime=" + bookingtime + "]";
	}

}
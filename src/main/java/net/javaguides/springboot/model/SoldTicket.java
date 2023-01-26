package net.javaguides.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.NumberFormat;


@Entity
@Table(name = "bookings")
public class SoldTicket {

	public SoldTicket() {
	}
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_ID")
	private Long id;

	
	@Column(name = "seatNumber")
	private int seatNumber;

	

	@Column(name = "purchasedTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchasedTime;

	@Column(name = "bookingStatus")
	private String bookingStatus;

	@Column(name = "finalPrice")
	@NumberFormat(pattern = "#,###.00")
	private float finalPrice;

	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	

	public Date getPurchasedTime() {
		return purchasedTime;
	}

	public void setPurchasedTime(Date purchasedTime) {
		this.purchasedTime = purchasedTime;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}




}


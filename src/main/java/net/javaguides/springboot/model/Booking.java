package net.javaguides.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "bookings")
public class Booking {

	public Booking() {
	}
	
	
	public Booking(int seatNumber) {
        this.seatNumber = seatNumber;
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_ID")
	private Long id;

	
	@Column(name = "seatNumber")
	private int seatNumber;

	@Column(name = "purchased_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchasedTime;

	@Column(name = "bookingStatus")
	private String bookingStatus;

	@Column(name = "final_price")
	private float finalPrice;

	@ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
	
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





	public float getFinalPrice() {
		return finalPrice;
	}


	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}


	public String getBookingStatus() {
		return bookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	public Trip getTrip() {
		return trip;
	}


	public void setTrip(Trip trip) {
		this.trip = trip;
	}




}


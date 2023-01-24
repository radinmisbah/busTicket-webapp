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


@Entity
@Table(name = "sold_ticket")
public class SoldTicket {

	public SoldTicket() {
	}
	
	
	public SoldTicket(int seatNumber, Date departureDate) {
        this.seatNumber = seatNumber;
        this.departureDate = departureDate;
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_ID")
	private Long id;

	
	@Column(name = "seatNumber")
	private int seatNumber;

	@Column(name = "departureDate")
	private Date departureDate;

	@Column(name = "purchasedTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchasedTime;

	
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

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getPurchasedTime() {
		return purchasedTime;
	}

	public void setPurchasedTime(Date purchasedTime) {
		this.purchasedTime = purchasedTime;
	}




}


package net.javaguides.springboot.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "trips")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Trip {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_ID")
	private Long id;

    @Column(name = "busName")
	private String busName;
	
	@Column(name = "departure")
	private String departure;
	
	@Column(name = "arrival")
	private String arrival;
	
	@Column(name = "maxSeat")
	private int maxSeat;
	
	@Column(name = "price")
    @NumberFormat(pattern = "#,###.00")
	private float price;
	
	@Column(name = "departureTime")
	private  LocalTime departureTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_ID", referencedColumnName = "bus_ID")
    private List<Booking> Bookings = new ArrayList<>();

    public Trip(){

    }
    public Trip(String busName, String departure, String arrival, int maxSeat, float price, LocalTime departureTime) {
        this.busName = busName;
        this.departure = departure;
        this.arrival = arrival;
        this.maxSeat = maxSeat;
        this.price = price;
        this.departureTime = departureTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getMaxSeat() {
        return maxSeat;
    }

    public void setMaxSeat(int maxSeat) {
        this.maxSeat = maxSeat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    

}

package net.javaguides.springboot.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.NumberFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "trips")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Trip {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "departure")
	private String departure;
	
	@Column(name = "arrival")
	private String arrival;

    @Column(name = "departureDate")
    @Temporal (TemporalType.DATE)
	private  Date departureDate;

    @Column(name = "departureTime")
	private  LocalTime departureTime;
	
	@Column(name = "maxSeat")
	private int maxSeat;
	
	@Column(name = "price")
    @NumberFormat(pattern = "#,###.00")
	private float price;

    @Column(name = "tripStatus")
	private String tripStatus;
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private List<Booking> Bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    public Bus getBus() {
        return bus;
    }
    public void setBus(Bus bus) {
        this.bus = bus;
    }
    public Trip(){

    }
    public Trip(String departure, String arrival, int maxSeat, float price, LocalTime departureTime) {
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }
}

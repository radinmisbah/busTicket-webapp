package net.javaguides.springboot.model;

import java.util.ArrayList;
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

@Entity
@Table(name = "buses")
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_ID")
	private Long id;

	
	@Column(name = "reg_number")
	private String regNumber;

	@Column(name = "driver_name")
	private String driverName;

    @ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
    Company company;

    public Bus() {
	}
	
	public Bus(String regNumber, String driverName) {
        this.regNumber = regNumber;
        this.driverName = driverName;
    }
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_ID", referencedColumnName = "bus_ID")
    private List<Trip> trips = new ArrayList<>();
	
}


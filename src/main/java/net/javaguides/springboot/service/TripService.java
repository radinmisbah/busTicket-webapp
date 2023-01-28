package net.javaguides.springboot.service;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import net.javaguides.springboot.model.Trip;


public interface TripService {
    public Long create(Trip bus);
    public Page<Trip> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    public void deleteBusById(long id);
    public Trip getBusById(long id);
    public List<Trip> searchBus(String departure, String arrival);
    public List<Trip> searchTrip(String departure, String arrival, Date departureDate);
    public List<Trip> findbydate(Date departureDate);
}

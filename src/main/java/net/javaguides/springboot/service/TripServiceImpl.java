package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import net.javaguides.springboot.model.Trip;
import net.javaguides.springboot.repository.TripRepository;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
    private TripRepository tripRepo;
	
	@Override
	@Transactional
	public Long create(Trip bus) {
        Long id = tripRepo.save(bus).getId();
		return id;
	}

	@Override
	public Trip getBusById(long id) {
		Optional<Trip> optional = tripRepo.findById(id);
		Trip bus = null;
		if (optional.isPresent()) {
			bus = optional.get();
		} else {
			throw new RuntimeException(" bus not found for id :: " + id);
		}
		return bus;
	}

	@Override
	public void deleteBusById(long id) {
		tripRepo.deleteById(id);
	}

	@Override
	public List<Trip> searchBus(String departure, String arrival) {
		return tripRepo.findByDepartureAndArrival(departure, arrival);
	}

	@Override
	public Page<Trip> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.tripRepo.findAll(pageable);
	}

	@Override
	public List<Trip> searchTrip(String departure, String arrival, Date departureDate) {

		List<Trip> trips = tripRepo.findAllByDepartureDate(departureDate);
		
				List<Trip> filteredList = new ArrayList<Trip>();
				trips.forEach(trip -> {
					if (trip.getDeparture().equalsIgnoreCase(departure) && trip.getArrival().equalsIgnoreCase(arrival)) {
						filteredList.add(trip);
					}
				});
				
	    return filteredList;
		
	}   
	
	@Override
	public List<Trip> findbydate(Date departureDate){
        return tripRepo.findAllByDepartureDate(departureDate);
	}
}


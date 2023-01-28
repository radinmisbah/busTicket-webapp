package net.javaguides.springboot.service;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import net.javaguides.springboot.model.Bus;
import net.javaguides.springboot.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepo;

    @Override
    public Page<Bus> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
        return this.busRepo.findAll(pageable);
    }

    @Override
    @Transactional
    public Long saveBus(Bus bus) {
        Long id = busRepo.save(bus).getId();
        return id;
    }

    @Override
    public void deleteBusById(long id) {
        busRepo.deleteById(id);
    }

    @Override
    public Bus getBusById(long id) {
        Optional<Bus> optional = busRepo.findById(id);
        Bus bus = null;
        if(optional.isPresent()) {
            bus = optional.get();
        } else {
            throw new RuntimeException("Bus is not found for id: " + id);
        }

        return bus;
    }

}

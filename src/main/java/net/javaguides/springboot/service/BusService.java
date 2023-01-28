package net.javaguides.springboot.service;

import java.util.List;
import org.springframework.data.domain.Page;
import net.javaguides.springboot.model.Bus;

public interface BusService {
    public Page<Bus> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    public Long saveBus(Bus bus);
    public void deleteBusById(long id);
    public Bus getBusById(long id);
    public List<Bus> findAll();
}

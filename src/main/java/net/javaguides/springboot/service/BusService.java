package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Bus;


public interface BusService {
    public Long create(Bus bus);

    Page<Bus> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    public void deleteBusById(long id);
    public Bus getBusById(long id);
    public List<Bus> searchBus(String departure, String destination);
}

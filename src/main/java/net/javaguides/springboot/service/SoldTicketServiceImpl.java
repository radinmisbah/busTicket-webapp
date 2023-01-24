package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.SoldTicket;
import net.javaguides.springboot.repository.SoldTicketRepository;

@Service
public class SoldTicketServiceImpl implements SoldTicketService {

    @Autowired
    private SoldTicketRepository soldTicketRepository;

    @Override
    public List<SoldTicket> getAllSoldTicket() {
        // TODO Auto-generated method stub
        return soldTicketRepository.findAll();
    }

    @Override
    public void saveTicket(SoldTicket soldTicket) {
        // TODO Auto-generated method stub
        this.soldTicketRepository.save(soldTicket);
        
    }
    
}

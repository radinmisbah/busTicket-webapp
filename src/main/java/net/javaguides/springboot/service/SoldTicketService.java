package net.javaguides.springboot.service;

import java.util.List;


import net.javaguides.springboot.model.SoldTicket;

public interface SoldTicketService {

    List<SoldTicket> getAllSoldTicket();
    void saveTicket (SoldTicket soldTicket);
    
}

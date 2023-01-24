package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.SoldTicket;

@Repository
public interface SoldTicketRepository extends JpaRepository <SoldTicket, Long>   {

    
}

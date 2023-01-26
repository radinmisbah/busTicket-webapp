package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.springboot.model.Bus;

public interface BusRepository extends JpaRepository <Bus, Long> {
    
}

package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    @Query(value = "SELECT * FROM companies WHERE id = 4", nativeQuery = true)
    public Company findId4();
}

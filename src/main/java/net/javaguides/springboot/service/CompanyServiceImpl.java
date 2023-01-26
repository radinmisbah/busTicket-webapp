package net.javaguides.springboot.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Company;
import net.javaguides.springboot.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    @Override
    public Page<Company> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
        return this.companyRepo.findAll(pageable);
    }

    @Override
    @Transactional
    public Long saveCompany(Company company) {
        Long id = companyRepo.save(company).getId();
        return id;
    }

    @Override
    public void deleteCompanyById(long id) {
        companyRepo.deleteById(id);
    }

    @Override
    public Company getCompanyById(long id) {
        Optional<Company> optional = companyRepo.findById(id);
        Company company = null;
        if(optional.isPresent()) {
            company = optional.get();
        } else {
            throw new RuntimeException("Company is not found for id: " + id);
        }

        return company;
    }

    public Company findId4() {
        return companyRepo.findId4();
    }
}

package net.javaguides.springboot.service;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Company;

public interface CompanyService {
    public Page<Company> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    public Long saveCompany(Company company);
    public void deleteCompanyById(long id);
    public Company getCompanyById(long id);
    public Company findId4();
}

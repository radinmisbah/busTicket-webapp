package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import net.javaguides.springboot.model.Company;
import net.javaguides.springboot.service.CompanyService;

@Controller
public class CompanyController {
    @Autowired
	private CompanyService companyService;

    //redirect to manage company page
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/companies")
	public String showManageCompany(Model model) {
        //redirected to paginated method
		return findPaginated(1, "name", "asc", model);		
	}

    //mapping for page if the the list too long
    @GetMapping("/admin/companies/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 15;
        
        Page<Company> page = companyService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Company> companyList = page.getContent();
        Company company = new Company();
        Company companyId4 = companyService.findId4();
        
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("companyList", companyList);
        model.addAttribute("company", company);
        model.addAttribute("companyId4", companyId4);
        
        return "company_index";
    }

    //redirect to form to add new company
    @GetMapping("/admin/companies/register")
	public String showRegisterCompanyForm(Model model) {
		// create model attribute to bind form data
		Company company = new Company();
		model.addAttribute("company", company);

		return "company_register_form";
	}

    //do http post request to save new company info
    @PostMapping("/admin/companies/save")
	public String saveCompany(@ModelAttribute("company") Company company) {
		// save company to database
		companyService.saveCompany(company);

		return "redirect:/admin/companies";
	}

    @GetMapping("/admin/companies/{id}/edit")
	public String showEditCompanyForm(@PathVariable ( value = "id") long id, Model model) {
		// get company from the service
		Company company = companyService.getCompanyById(id);
		// set company as a model attribute to pre-populate the form
		model.addAttribute("company", company);

		return "company_edit_form";
	}

    @GetMapping("/admin/companies/{id}/delete")
	public String deleteCompany(@PathVariable (value = "id") long id) {
		// call delete company method 
		companyService.deleteCompanyById(id);

		return "redirect:/admin/companies";
	}
}

package net.javaguides.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	public User getCurrentUser();
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

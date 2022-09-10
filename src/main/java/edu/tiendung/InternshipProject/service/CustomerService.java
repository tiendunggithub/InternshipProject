package edu.tiendung.InternshipProject.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.tiendung.InternshipProject.entity.Customer;

public interface CustomerService {

	Page<Customer> findAll(Pageable pageable);
	
	Page<Customer> findAllByFullNameContaining(String fullName, Pageable pageable);
	
	Customer save(Customer customer);
	
	Customer update(Long id, Customer customer);
	
	boolean deleteById(Long id);
	
	Customer findById(Long id);
	
	Customer findByEmail(String email);
	
	Customer findByEmailAndPassword(String email, String password);
	
	Customer getUserDetailsById(Long userId) throws Exception;
}

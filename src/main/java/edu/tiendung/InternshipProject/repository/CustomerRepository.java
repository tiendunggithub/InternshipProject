package edu.tiendung.InternshipProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tiendung.InternshipProject.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Page<Customer> findAll(Pageable pageable);
	
	Page<Customer> findAllByFullNameContaining(String fullName, Pageable pageable);
	
	Customer findByEmail(String email);
	
	Customer findByEmailAndPassword(String email, String password);
}

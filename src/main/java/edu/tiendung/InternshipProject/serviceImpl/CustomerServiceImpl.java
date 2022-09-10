package edu.tiendung.InternshipProject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Customer;
import edu.tiendung.InternshipProject.repository.CustomerRepository;
import edu.tiendung.InternshipProject.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer update(Long id, Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public boolean deleteById(Long id) {
		if(id >= 1) {
			customerRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Customer findById(Long id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	@Override
	public Page<Customer> findAllByFullNameContaining(String fullName, Pageable pageable) {
		return customerRepository.findAllByFullNameContaining(fullName, pageable);
	}

	@Override
	public Customer findByEmailAndPassword(String email, String password) {
		return customerRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public Customer getUserDetailsById(Long userId) throws Exception{
		return customerRepository.findById(userId).orElseThrow(()-> new Exception("User not found.."));
		
	}
}

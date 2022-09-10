package edu.tiendung.InternshipProject.JwtConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Customer;
import edu.tiendung.InternshipProject.service.CustomerService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	CustomerService customerService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	public UserDetails loadUserById(Long id) {
		try {
			Customer customer = customerService.getUserDetailsById(id);
			return UserPrincipal.create(customer);
		}catch(Exception e) {
			throw new UsernameNotFoundException("User not fount with id " + id);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Customer customer = customerService.findByEmail(email);
			return UserPrincipal.create(customer);
		}catch(Exception e) {
			throw new UsernameNotFoundException("User not found with Email" + email);
		}
	}

}

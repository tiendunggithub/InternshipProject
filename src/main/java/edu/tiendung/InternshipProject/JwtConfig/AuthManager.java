package edu.tiendung.InternshipProject.JwtConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import edu.tiendung.InternshipProject.entity.Admin;
import edu.tiendung.InternshipProject.entity.Customer;
import edu.tiendung.InternshipProject.service.AdminService;
import edu.tiendung.InternshipProject.service.CustomerService;

@Configuration
public class AuthManager {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AdminService adminService;
	private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
	
	public Authentication authenticate(UsernamePasswordAuthenticationToken authentication, Customer loginRequest) throws AuthenticationException{
		String email = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";
		Customer customer;
		try {
			logger.info("user is going validate(AuthManager) " + email);
			if(customerService == null) {
				logger.info("user found the error");
				 throw new BadCredentialsException("1001");
			}
			customer = customerService.findByEmail(email);
			if (customer == null) {
		        throw new BadCredentialsException("User Not found!!");
			}
			logger.info("from authentication "+password+" from db "+customer.getPassword());
			if(!this.passwordMatch(password, customer.getPassword())) {
				logger.info("Password is wrong for user .."+customer.getEmail());	
				throw new BadCredentialsException("Email or password is wrong.");
			} 
			return new UsernamePasswordAuthenticationToken(new UserPrincipal(customer.getId(), email, password), password);
		}catch(Exception e) {
			logger.info("Error",e);
			 throw new BadCredentialsException(e.getMessage());
		}	
	}
	public Authentication authenticateAdmin(UsernamePasswordAuthenticationToken authentication, Admin loginRequest) throws AuthenticationException{
		String username = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";
		Admin admin;
		try {
			logger.info("user is going validate(AuthManager) " + username);
			if(customerService == null) {
				logger.info("user found the error");
				 throw new BadCredentialsException("1001");
			}
			admin = adminService.findByUsername(username);
			if (admin == null) {
		        throw new BadCredentialsException("User Not found!!");
			}
			logger.info("from authentication "+password+" from db "+admin.getPass());
			if(!this.passwordMatch(password, admin.getPass())) {
				logger.info("Password is wrong for user .."+admin.getUsername());	
				throw new BadCredentialsException("Email or password is wrong.");
			} 
			return new UsernamePasswordAuthenticationToken(new UserPrincipal(admin.getId(), username, password), password);
		}catch(Exception e) {
			logger.info("Error",e);
			 throw new BadCredentialsException(e.getMessage());
		}	
	}
	private Boolean passwordMatch(String rawPassword,String from_db_encoded) {
		 return rawPassword.equals(from_db_encoded);
		// return BCrypt.checkpw(rawPassword.toString(),from_db_encoded);	 
	 }
}

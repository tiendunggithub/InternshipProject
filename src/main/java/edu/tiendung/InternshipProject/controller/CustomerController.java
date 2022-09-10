package edu.tiendung.InternshipProject.controller;

import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tiendung.InternshipProject.JwtConfig.AuthManager;
import edu.tiendung.InternshipProject.JwtConfig.JwtTokenProvider;
import edu.tiendung.InternshipProject.JwtConfig.UserPrincipal;
import edu.tiendung.InternshipProject.domain.Response;
import edu.tiendung.InternshipProject.entity.Customer;
import edu.tiendung.InternshipProject.service.CustomerService;

@RestController
@CrossOrigin(origins="*", maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthManager aMan;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	//post and get
	@RequestMapping("/status")
	public ResponseEntity<?> serverStatus(){
		String mess = "Server is running";
		return new ResponseEntity<>(mess, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Customer> customers = customerService.findAll(pageable);
		if(customers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
		return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public Boolean deleteCustomer(@PathVariable("id") Long id) {
		return customerService.deleteById(id);
	}
	
	
	@RequestMapping("login")//post and get
	public ResponseEntity<?> userLogin(@RequestBody Customer loginRequest) {
		
		try {
			
        	Authentication authentication =  aMan.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()) ,loginRequest);
        	SecurityContextHolder.getContext().setAuthentication(authentication);
        	String token = tokenProvider.generateToken(authentication);
    		JSONObject obj =  this.getUserResponse(token);
    		if(obj == null) {
    			throw new Exception("Error while generating Reponse");
    		}
    		
	        return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
    	}catch(Exception e ) {
    		logger.info("Error in authenticateUser ",e);
    		String mess = "error";
    		return ResponseEntity.ok(mess);
    	}
	}
	private JSONObject getUserResponse(String token) {
		try {
			Customer customer = customerService.getUserDetailsById(_getUserId());
			HashMap<String,String> response = new HashMap<String,String>();
			response.put("user_id", ""+_getUserId());
			response.put("email", customer.getEmail());
			response.put("full_name", customer.getFullName());
			response.put("phone", customer.getPhone());
			response.put("address", customer.getAddress());		
		
			JSONObject obj = new JSONObject();
			
			obj.put("user_profile_details",response);
			obj.put("token", token);
			return obj;
		} catch (Exception e) {
			logger.info("Error in getUserResponse ",e);
		}
    	return null;
	}

	private Long _getUserId() {
		logger.info("user id vaildating. "+ SecurityContextHolder.getContext().getAuthentication());
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("(LoginController)user id is "+userPrincipal.getId());
		return userPrincipal.getId();
	}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Customer customer){
//		Customer customer1 = customerService.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
//		if(customer1 == null) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return new ResponseEntity<>(customer1, HttpStatus.OK);
//	}
	
	//resgitration
	@PostMapping("/registration")
	public ResponseEntity<?> registration(@RequestBody Customer customer) throws Exception{
		String email = customer.getEmail();
		if(email != null && !"".equals(email)) {
			Customer customer1 = customerService.findByEmail(email);
			if(customer1 != null) {
				throw new Exception("user with "+ email +" is already exist");
			}
		}
		Customer customer1 = null;
		customer1 = customerService.save(customer);
		return new ResponseEntity<>(customer1, HttpStatus.OK);
		
	}
}

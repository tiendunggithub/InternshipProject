package edu.tiendung.InternshipProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.tiendung.InternshipProject.JwtConfig.AuthManager;
import edu.tiendung.InternshipProject.JwtConfig.JwtTokenProvider;
import edu.tiendung.InternshipProject.JwtConfig.UserPrincipal;
import edu.tiendung.InternshipProject.entity.Admin;
import edu.tiendung.InternshipProject.entity.Customer;
import edu.tiendung.InternshipProject.entity.Role;
import edu.tiendung.InternshipProject.service.AdminService;
import edu.tiendung.InternshipProject.service.BaseService;
import edu.tiendung.InternshipProject.service.RoleService;


@RestController
@CrossOrigin(origins= "*", maxAge = 4800, allowCredentials = "false")
//@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleService roleSer;
	
	@Autowired
	AuthManager aMan;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	//API Lấy danh sách
	@GetMapping("/list") 
//	public List<Admin> getAllAdmin(){
//		return adminService.getAllAdmin(); 
//	}
	public ResponseEntity<List<Admin>> getAllAdmin(){
		return new ResponseEntity<>(adminService.getAllAdmin(), HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> pageAdmin(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Admin> admins = adminService.findAllAdmin(pageable);
		if(admins.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(admins ,HttpStatus.OK);
	}
	
	@GetMapping("/page/search/{name}")
	public ResponseEntity<?> pageAdmin(@PathVariable String name ,@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Admin> admins = adminService.findAllByNameContaining(name, pageable);
		if(admins.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(admins ,HttpStatus.OK);
	}
	
//	@GetMapping("/page/search")
//	public ResponseEntity<?> pageAdmin(@RequestParam("name") String search ,@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
//		Page<Admin> admins = adminService.findAllByNameQuery(search, pageable);
//		if(admins.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(admins ,HttpStatus.OK);
//	}
	
	@GetMapping("/role")
	public List<Role> getAllRole(){
		return roleSer.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Admin> getadmin(@PathVariable Long id) {
		return new ResponseEntity<>(adminService.findById(id), HttpStatus.OK);
	}
	
	//API Thêm mới
	@PostMapping("/create")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);	
	}
	
	//API cập nhật
//	@PutMapping("/update")
//	public Admin updateAdmin(@RequestParam("id") long id, @RequestBody Admin admin) {
//		return adminService.updateAdmin(id, admin);
//	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin){
		Admin adminEncontrado = adminService.findById(id);
		
		if(adminEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			adminEncontrado.setName(admin.getName());
			adminEncontrado.setUsername(admin.getUsername());
			adminEncontrado.setPass(admin.getPass());
			adminEncontrado.setRole(admin.getRole());
			return new ResponseEntity<>(adminService.addAdmin(adminEncontrado), HttpStatus.CREATED);
		}catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//API Xóa
	@DeleteMapping("/delete/{id}")
	public Boolean deleteAdmin(@PathVariable("id") Long id) {
		return adminService.deleteAdmin(id);
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Admin admin){
//		System.out.println(admin);
//		Admin admin1 = adminService.findByUsername(admin.getUsername());
//		System.out.println(admin1);
//		if(admin1.getPass().equals(admin.getPass())){
//			return ResponseEntity.ok(admin1);
//		}
//		return (ResponseEntity<?>) ResponseEntity.internalServerError();
//	}
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Admin admin){
//		Admin admin1 =adminService.findByUsernameAndPass(admin.getUsername(), admin.getPass());
//		System.out.println(admin1);
//		if(admin1 == null) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return ResponseEntity.ok(admin1);
//	}
	
	/*
	 * @GetMapping("/list") public String index(Model model) {
	 * model.addAttribute("admins", adminService.findAll()); return"list"; }
	 */
	/*
	 * @GetMapping("/list") public String listAdmin(Model themodel) { List<Admin>
	 * theAdmins = baseService.getAll(); themodel.addAttribute("admins", theAdmins);
	 * return"list"; }
	 */
	@RequestMapping("login")//post and get
	public ResponseEntity<?> userLogin(@RequestBody Admin loginRequest) {
		try {
        	Authentication authenticationAdmin =  aMan.authenticateAdmin(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPass()) ,loginRequest);
        	SecurityContextHolder.getContext().setAuthentication(authenticationAdmin);
        	String token = tokenProvider.generateToken(authenticationAdmin);
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
			Admin admin = adminService.getUserDetailsById(_getUserId());
			HashMap<String,String> response = new HashMap<String,String>();
			response.put("id", ""+_getUserId());
			response.put("user_name", admin.getUsername());
			response.put("pass", admin.getPass());
			response.put("full_name", admin.getName());
			response.put("role_id",""+admin.getRole());	
		
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
}

package edu.tiendung.InternshipProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import edu.tiendung.InternshipProject.entity.Admin;
import edu.tiendung.InternshipProject.servicedefine.AdminService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	//API Lấy danh sách
	@GetMapping("/list") 
	public List<Admin> getAllAdmin(){
		return adminService.getAllAdmin(); 
	}
	
	//API Thêm mới
	@PostMapping("/add")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);	
	}
	
	//API cập nhật
	@PutMapping("/update")
	public Admin updateAdmin(@RequestParam("id") long id, @RequestBody Admin admin) {
		return adminService.updateAdmin(id, admin);
	}
	
	//API Xóa
	@DeleteMapping("/delete/{id}")
	public Boolean deleteAdmin(@PathVariable("id") long id) {
		return adminService.deleteAdmin(id);
	}
	
	/*
	 * @GetMapping("/list") public String index(Model model) {
	 * model.addAttribute("admins", adminService.findAll()); return"list"; }
	 */
	/*
	 * @GetMapping("/list") public String listAdmin(Model themodel) { List<Admin>
	 * theAdmins = baseService.getAll(); themodel.addAttribute("admins", theAdmins);
	 * return"list"; }
	 */
}

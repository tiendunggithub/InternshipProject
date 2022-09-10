package edu.tiendung.InternshipProject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import edu.tiendung.InternshipProject.entity.Admin;
import edu.tiendung.InternshipProject.entity.Customer;

public interface AdminService {
	
	//Hàm lấy danh sách admin
	List<Admin> getAllAdmin();
	
	//Hàm thêm admin
	public Admin addAdmin(Admin admin);
	
	public Admin updateAdmin(Long id, Admin admin);
	
	//Hàm xóa admin
    public boolean deleteAdmin(Long id);
    
    public Admin findById (Long id);
    
    Page<Admin> findAllAdmin(Pageable pageable);
    
    //Tìm kiếm theo tên
    Page<Admin> findAllByNameContaining(String name, Pageable pageable);     
    
    Page<Admin> findAllByNameQuery(@Param("name") String name, Pageable pageable);
    
    Admin findByUsername(String username);
    
    Admin findByUsernameAndPass(String username, String pass);
    
    Admin getUserDetailsById(Long id) throws Exception;
}

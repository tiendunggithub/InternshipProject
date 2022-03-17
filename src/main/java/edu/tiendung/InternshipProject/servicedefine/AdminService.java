package edu.tiendung.InternshipProject.servicedefine;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.tiendung.InternshipProject.entity.Admin;

public interface AdminService {
	
	//Hàm lấy danh sách admin
	List<Admin> getAllAdmin();
	
	//Hàm thêm admin
	public Admin addAdmin(Admin admin);
	
	public Admin updateAdmin(Long id, Admin admin);
	
	//Hàm xóa admin
    public boolean deleteAdmin(Long id);
    
    public Admin findById (long id);
    
    Page<Admin> findAllAdmin(Pageable pageable);
    
    //Tìm kiếm theo tên
    Page<Admin> findAllByNameContaining(String name, Pageable pageable);
}

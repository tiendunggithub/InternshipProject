package edu.tiendung.InternshipProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Admin;
import edu.tiendung.InternshipProject.repository.AdminRepository;
import edu.tiendung.InternshipProject.servicedefine.AdminService;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepo;
	
	@Override
	public List<Admin> getAllAdmin() {
		return adminRepo.findAll();
	}
	
	@Override
	public Admin updateAdmin(Long id, Admin admin) {
		if(admin != null) {
			Admin admin1 =  adminRepo.getById(id);
			if(admin1!=null) {
				admin1.setName(admin.getName());
				admin1.setUsername(admin.getUsername());
				admin1.setPass(admin.getPass());
				
				return adminRepo.save(admin1);
			}
		}
		return null;
	}
	
	@Override
	public Admin addAdmin(Admin admin) {
		if(admin != null) {
			return adminRepo.save(admin);
		}
		return null;
	}
	
	@Override
	public boolean deleteAdmin(Long id) {
		if (id >= 1) {
			adminRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public Admin findById(long id) {
		return adminRepo.getById(id);
	}
	
	@Override
	public Page<Admin> findAllByNameContaining(String name, Pageable pageable) {
		return adminRepo.findAllByNameContaining(name, pageable);
	}

	@Override
	public Page<Admin> findAllAdmin(Pageable pageable) {
		return adminRepo.findAll(pageable);
	}		
}

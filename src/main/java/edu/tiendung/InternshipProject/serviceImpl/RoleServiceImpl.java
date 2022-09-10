package edu.tiendung.InternshipProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Role;
import edu.tiendung.InternshipProject.repository.RoleRepository;
import edu.tiendung.InternshipProject.service.BaseService;
@Service
public class RoleServiceImpl implements BaseService<Role>{

	@Autowired
	private RoleRepository roleRepo;
	@Override
	public List<Role> getAll() {
		return roleRepo.findAll();
	}

	@Override
	public Role save(Role object) {
		return roleRepo.save(object);
		
	}

	@Override
	public Role getByID(Long id) {
		return roleRepo.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
		roleRepo.deleteById(id);
	}

}

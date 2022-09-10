package edu.tiendung.InternshipProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Role;
import edu.tiendung.InternshipProject.repository.RoleRepository;
import edu.tiendung.InternshipProject.service.RoleService;
@Service
public class RoleServiceImpl1 implements RoleService {

	@Autowired
	private RoleRepository roleRepo;
	@Override
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

}

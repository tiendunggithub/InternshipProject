package edu.tiendung.InternshipProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Size;
import edu.tiendung.InternshipProject.repository.SizeReponsitory;
import edu.tiendung.InternshipProject.service.BaseService;

@Service
public class SizeServiceImpl implements BaseService<Size>{

	@Autowired
	private SizeReponsitory sizeRepo;
	@Override
	public List<Size> getAll() {
		return sizeRepo.findAll();
	}

	@Override
	public Size save(Size object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Size getByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}

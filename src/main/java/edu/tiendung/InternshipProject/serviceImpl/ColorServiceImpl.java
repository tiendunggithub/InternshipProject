package edu.tiendung.InternshipProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Color;
import edu.tiendung.InternshipProject.repository.ColorRepository;
import edu.tiendung.InternshipProject.service.BaseService;

@Service
public class ColorServiceImpl implements BaseService<Color>{
	@Autowired
	private ColorRepository colorRepo;
	@Override
	public List<Color> getAll() {
		return colorRepo.findAll();
	}

	@Override
	public Color save(Color object) {
		return null;
		
	}

	@Override
	public Color getByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}

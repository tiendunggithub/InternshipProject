package edu.tiendung.InternshipProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.Category;
import edu.tiendung.InternshipProject.repository.CategoryRepository;
import edu.tiendung.InternshipProject.service.BaseService;
@Service
public class CategoryServiceImpl implements BaseService<Category>{

	@Autowired
	private CategoryRepository cateRepo;
	@Override
	public List<Category> getAll() {
		return cateRepo.findAll();
	}

	@Override
	public Category save(Category category) {
		return cateRepo.save(category);
		
	}

	@Override
	public Category getByID(Long id) {
		return cateRepo.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
		cateRepo.deleteById(id);
	}

}

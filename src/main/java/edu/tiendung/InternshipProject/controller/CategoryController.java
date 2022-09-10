package edu.tiendung.InternshipProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tiendung.InternshipProject.entity.Category;
import edu.tiendung.InternshipProject.service.BaseService;

@RestController
@CrossOrigin(origins= "*", maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private BaseService<Category> cateService;

	@GetMapping("/list")
	private ResponseEntity<List<Category>> getAllCategory(){
		return new ResponseEntity<>(cateService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("create")
	private Category createCategory(@RequestBody Category category){
		return cateService.save(category);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getProduct(@PathVariable Long id) {
		return new ResponseEntity<>(cateService.getByID(id), HttpStatus.OK);
	}
}

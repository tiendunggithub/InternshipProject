package edu.tiendung.InternshipProject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import edu.tiendung.InternshipProject.entity.Products;

public interface ProductService {
	List<Products> getAllProduct();
	
	public Products save(Products product);
	
	public Products update(Long id, Products product);
	
	public boolean deleteById(Long id);
	
	public Products findById(Long id);
	
	Page<Products> findAllByNameContaining(String name,Pageable pageable);
	
	Page<Products> findAllProduct(Pageable pageable);
	
//	Page<Product> getByCategoryId(String category_id, Pageable pageable);
	
	Page<Products> getProductsByCategory(String product_id, Pageable pageable);
	
	Page<Products> findAllProductByCategoryId(Long product_id, Pageable pageable);
}

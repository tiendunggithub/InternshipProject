package edu.tiendung.InternshipProject.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.tiendung.InternshipProject.entity.Products;
import edu.tiendung.InternshipProject.repository.ProductRepository;
import edu.tiendung.InternshipProject.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	@Override
	public List<Products> getAllProduct() {
		return productRepo.findAll();
	}

	@Override
	public Products save(Products product) {
		return productRepo.save(product);
	}

	@Override
	public Products update(Long id, Products product) {
		Optional<Products> prod = productRepo.findById(id);
			Products  product1 = prod.get();
			product1.setName(product.getName());
			product1.setImage(product.getImage());
			product1.setPrice(product.getPrice());
			product1.setPromotion(product.getPromotion());
			product1.setDescription(product.getDescription());
			product1.setCategory(product.getCategory());
			product1.setListColor(product.getListColor());
			product1.setListSize(product.getListSize());
			return productRepo.save(product1);
	}

	@Override
	public boolean deleteById(Long id) {
		if (id >= 1) {
			productRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Products findById(Long id) {
		return productRepo.findById(id).get();
	}

	@Override
	public Page<Products> findAllByNameContaining(String name,Pageable pageable) {
		return productRepo.findAllByNameQuery(name,pageable);
	}

	@Override
	public Page<Products> findAllProduct(Pageable pageable) {
		return productRepo.findAll(pageable);
	}

	@Override
	public Page<Products> getProductsByCategory(String product_id, Pageable pageable) {
		return productRepo.getByCategoryId(product_id,pageable);
	}

	@Override
	public Page<Products> findAllProductByCategoryId(Long product_id, Pageable pageable) {
		return productRepo.findAllProductByCategoryId(product_id, pageable);
	}

//	@Override
//	public Page<Product> getByCategoryId(String category_id, Pageable pageable) {
//		return productRepo.getByCateId(category_id, pageable);
//	}

}

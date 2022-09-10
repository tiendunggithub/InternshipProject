package edu.tiendung.InternshipProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.tiendung.InternshipProject.entity.Admin;
import edu.tiendung.InternshipProject.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{
	Page<Products> findAllByNameContaining(String name,Pageable pageable);
	
	Page<Products> findAll(Pageable pageable);
	
//	@Query("Select prod FROM products prod WHERE prod.cate_id=:category_id")
//	Page<Product> getByCateId(@Param("category_id")String category_id, Pageable pageable);
	
	@Query(value="SELECT * FROM Products prod WHERE prod.cate_id=:cate_id", nativeQuery = true)
	Page<Products> getByCategoryId(@Param("cate_id")String cate_id, Pageable pageable);
	
	Page<Products> findAllProductByCategoryId(@Param("cate_id")Long cate_id, Pageable pageable);
	
	@Query("SELECT c FROM Products AS c WHERE c.name LIKE CONCAT('%', :name, '%') ")
	Page<Products> findAllByNameQuery(@Param("name") String name, Pageable pageable);
}

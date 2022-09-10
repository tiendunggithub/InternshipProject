package edu.tiendung.InternshipProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import edu.tiendung.InternshipProject.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Page<Admin> findAllByNameContaining(String name, Pageable pageable);
	
	Page<Admin> findAll(Pageable pageable);
	
	@Query("SELECT c FROM Admin AS c WHERE c.name LIKE CONCAT('%', :name, '%') ")
	Page<Admin> findAllByNameQuery(@Param("name") String name, Pageable pageable);
	
	Admin findByUsername(String username);
	
	Admin findByUsernameAndPass(String username, String pass);
}

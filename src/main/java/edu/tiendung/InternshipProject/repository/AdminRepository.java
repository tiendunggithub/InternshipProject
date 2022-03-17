package edu.tiendung.InternshipProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.tiendung.InternshipProject.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Page<Admin> findAllByNameContaining(String name, Pageable pageable);

}

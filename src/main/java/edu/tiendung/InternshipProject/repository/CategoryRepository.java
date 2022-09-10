package edu.tiendung.InternshipProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tiendung.InternshipProject.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

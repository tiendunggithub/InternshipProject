package edu.tiendung.InternshipProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tiendung.InternshipProject.entity.Size;

@Repository
public interface SizeReponsitory extends JpaRepository<Size, Long>{

}

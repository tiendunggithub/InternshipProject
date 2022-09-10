package edu.tiendung.InternshipProject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tiendung.InternshipProject.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
}

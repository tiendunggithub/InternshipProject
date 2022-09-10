package edu.tiendung.InternshipProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.tiendung.InternshipProject.entity.CheckoutCart;

@Repository
public interface CheckoutRepo extends JpaRepository<CheckoutCart, Long> {

	@Query("SELECT c FROM CheckoutCart c WHERE c.user_id=:user_id")
	List<CheckoutCart> getCartByuserId(@Param("user_id") Long user_id);
}

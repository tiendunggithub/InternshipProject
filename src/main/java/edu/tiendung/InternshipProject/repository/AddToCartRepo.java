package edu.tiendung.InternshipProject.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.tiendung.InternshipProject.entity.AddToCart;

@Repository
public interface AddToCartRepo extends JpaRepository<AddToCart, Long> {

	@Query("Select sum(c.promotion) FROM AddToCart c WHERE c.user_id=:user_id")
	Double getTotalAmountByUserId(@Param("user_id")Long user_id);
	
	@Query("SELECT addCart FROM AddToCart addCart WHERE addCart.user_id=:user_id")
	List<AddToCart> getCartByuserId(@Param("user_id")Long user_id);
	
	
	@Query("SELECT addCart FROM AddToCart addCart WHERE addCart.product.id=:prod_id and addCart.user_id=:user_id")
	Optional<AddToCart> getCartByProductIdAnduserId(@Param("prod_id")Long prod_id,@Param("user_id")Long user_id);
	
	@Modifying
    @Transactional
	@Query("DELETE FROM AddToCart addCart WHERE addCart.id=:cart_id and addCart.user_id=:user_id")
	void deleteCartByIdAndUserId(@Param("cart_id")Long cart_id, @Param("user_id")Long user_id);
	
	@Modifying
    @Transactional
	@Query("DELETE FROM AddToCart d WHERE d.user_id=:user_id")
	void deleteAllCartByUserId(@Param("user_id")Long user_id);
	
	@Modifying
    @Transactional
	@Query("UPDATE AddToCart d SET d.qty=:qty, d.promotion=:promotion WHERE d.id=:cart_id")
	void updateQtyByCartId( @Param("cart_id")Long cart_id, @Param("promotion")double price,@Param("qty")Integer qty);
	
	
//	@Query("update AddToCart addCart set addCart.qty=:qty,addCart.price=:price WHERE addCart.id=:cart_id")
//	void updateQtyByCartId(@Param("cart_id")Long cart_id,@Param("price")double price,@Param("qty")Integer qty);
}

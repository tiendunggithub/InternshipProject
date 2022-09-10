package edu.tiendung.InternshipProject.service;

import java.util.List;

import edu.tiendung.InternshipProject.entity.AddToCart;
import edu.tiendung.InternshipProject.entity.CheckoutCart;

public interface CartService {
	List<AddToCart> addCartbyUserIdAndProductId(Long userId, Long prodId, int qty, double price, double promotion) throws Exception;
	
	void updateQtyByCartId(Long cartId, double promotion, int qty) throws Exception;
	
	List<AddToCart> getCartByUserId(Long userId);
	
	List<AddToCart> removeCartByUserId(Long cartId, Long userId);
	
	List<AddToCart> removeAllCartByUserId(Long userId);
	
	Boolean checkTotalAmountAgainstCart(double totalAmount,Long userId);
	
	List<CheckoutCart> getAllCheckoutByUserId(Long userId);
	
	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp)  throws Exception;
}

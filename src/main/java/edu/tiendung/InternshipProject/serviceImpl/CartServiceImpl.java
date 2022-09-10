package edu.tiendung.InternshipProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tiendung.InternshipProject.entity.AddToCart;
import edu.tiendung.InternshipProject.entity.CheckoutCart;
import edu.tiendung.InternshipProject.entity.Products;
import edu.tiendung.InternshipProject.repository.AddToCartRepo;
import edu.tiendung.InternshipProject.repository.CheckoutRepo;
import edu.tiendung.InternshipProject.service.CartService;
import edu.tiendung.InternshipProject.service.ProductService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	AddToCartRepo cartRepo;
	@Autowired
	CheckoutRepo checkoutRepo;
	@Autowired
	ProductService prodService;
	@Override
	public List<AddToCart> addCartbyUserIdAndProductId(Long userId, Long prodId, int qty, double price, double promotion) throws Exception {
		try {
			if(cartRepo.getCartByProductIdAnduserId(prodId, userId).isPresent()) {
//				throw new Exception("Product is already exist!!");
				
			}
			AddToCart obj = new AddToCart();
			obj.setQty(qty);
			obj.setUser_id(userId);
			Products pro = prodService.findById(prodId);
			obj.setProduct(pro);
			obj.setPrice(price);
			obj.setPromotion(promotion);
			
			cartRepo.save(obj);
			return this.getCartByUserId(userId);
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		
	}

	@Override
	public List<AddToCart> getCartByUserId(Long userId) {
		return cartRepo.getCartByuserId(userId);
	}

	@Override
	public List<AddToCart> removeCartByUserId(Long cartId, Long userId) {
		cartRepo.deleteCartByIdAndUserId(cartId, userId);
		return this.getCartByUserId(userId);
	}

	@Override
	public void updateQtyByCartId(Long cartId, double promotion, int qty) throws Exception {
		cartRepo.updateQtyByCartId(cartId, promotion, qty);
	}

	@Override
	public Boolean checkTotalAmountAgainstCart(double totalAmount, Long userId) {
		System.out.println("userId: "+userId);
		double total_amount = cartRepo.getTotalAmountByUserId(userId);
		System.out.println("total_amount: "+total_amount);
		System.out.println("total_amount: "+totalAmount);
		if(total_amount == totalAmount) {
			return true;
		}
		System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount+" ");
		return false;
	}

	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(Long userId) {
		return checkoutRepo.getCartByuserId(userId);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			long user_id = tmp.get(0).getUser_id();
			if(tmp.size() >0) {
				checkoutRepo.saveAll(tmp);
				this.removeAllCartByUserId(user_id);
				return this.getAllCheckoutByUserId(user_id);
			}	
			else {
				throw  new Exception("Should not be empty");
			}
		}catch(Exception e) {
			throw new Exception("Error while checkout "+e.getMessage());
		}
	}

	@Override
	public List<AddToCart> removeAllCartByUserId(Long userId) {
		cartRepo.deleteAllCartByUserId(userId);
		return null;
	}

}

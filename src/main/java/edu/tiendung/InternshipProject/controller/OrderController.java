package edu.tiendung.InternshipProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tiendung.InternshipProject.config.ShoppingCartConfig;
import edu.tiendung.InternshipProject.entity.AddToCart;
import edu.tiendung.InternshipProject.entity.CheckoutCart;
import edu.tiendung.InternshipProject.service.CartService;

@RestController
@CrossOrigin(origins= "*", maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/v1/order")
public class OrderController {
	
	@Autowired
	CartService cartService;
	
	@RequestMapping("/checkoutCart")
	public ResponseEntity<?> checkoutCart(@RequestBody HashMap<String, String> request){
		try {
			String keys[] = {"userId","total_price", "payment_type", "deliveryAddress"};
//			if(ShoppingCartConfig.validationWithHashMap(keys, addCartRequest)) {
//				
//			}
				Long user_Id = Long.parseLong(request.get("userId"));
				double total_amt = Double.parseDouble(request.get("total_price"));
			if(cartService.checkTotalAmountAgainstCart(total_amt, user_Id)) {
				List<AddToCart> cartItems = cartService.getCartByUserId(user_Id);
				List<CheckoutCart> tmp = new ArrayList<CheckoutCart>();
				for(AddToCart addCart : cartItems) {
					String orderId = ""+getOrderId();
					CheckoutCart cart = new CheckoutCart();
					cart.setPayment_type(request.get("pay_type"));
					cart.setPrice(total_amt);
					cart.setUser_id(user_Id);
					cart.setOrder_id(orderId);
					cart.setProduct(addCart.getProduct());
					cart.setQty(addCart.getQty());
					cart.setDelivery_address(request.get("deliveryAddress"));
					tmp.add(cart);
				}
				cartService.saveProductsForCheckout(tmp);
				String mess = "Oder placed successfully";
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				throw new Exception("Total amount is mismatch");
			}
		}catch(Exception e) {
			e.printStackTrace();
//			throw new Exception("Error is "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public int getOrderId(){
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}
	@GetMapping("/getOrderByUserId")
	public ResponseEntity<?> getOrderByUserId(@RequestBody HashMap<String, String> orderRequest) {
		try {
			String keys[] = {"userId"};
			String mess = "Oder placed successfully";
			return ResponseEntity.ok(mess);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

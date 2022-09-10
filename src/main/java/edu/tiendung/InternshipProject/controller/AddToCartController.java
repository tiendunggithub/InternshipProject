package edu.tiendung.InternshipProject.controller;

import java.util.HashMap;
import java.util.List;

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
import edu.tiendung.InternshipProject.service.CartService;

@RestController
@CrossOrigin(origins= "*", maxAge = 4800, allowCredentials = "false")
@RequestMapping("/api/v1/cart")
public class AddToCartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping("/status")
	public ResponseEntity<?> serverStatus(){
		String mess = "Server is running";
		return new ResponseEntity<>(mess, HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addCartWithProduct(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = {"userId", "prodId", "qty", "price", "promotion"};
			if(ShoppingCartConfig.validationWithHashMap(keys, addCartRequest)) {
				
			}
			Long userId = Long.parseLong(addCartRequest.get("userId"));
			Long prodId = Long.parseLong(addCartRequest.get("prodId"));
			int qty = Integer.parseInt(addCartRequest.get("qty"));
			double price = Double.parseDouble(addCartRequest.get("price"));
			double promotion = Double.parseDouble(addCartRequest.get("promotion"));
			
//			Long color = Long.parseLong(addCartRequest.get("colorId"));

			List<AddToCart> obj = cartService.addCartbyUserIdAndProductId(userId, prodId, qty, price, promotion);
			return ResponseEntity.ok(obj);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/updateQty")
	public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = {"cartId", "userId", "qty", "promotion"};
			if(ShoppingCartConfig.validationWithHashMap(keys, addCartRequest)) {
				
			}
				Long cartId = Long.parseLong(addCartRequest.get("cartId"));
				Long userId = Long.parseLong(addCartRequest.get("userId"));
				int qty = Integer.parseInt(addCartRequest.get("qty"));
				double promotion = Double.parseDouble(addCartRequest.get("promotion"));
				cartService.updateQtyByCartId(cartId, promotion, qty);
				List<AddToCart> obj = cartService.getCartByUserId(userId);
				return ResponseEntity.ok(obj);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/removeProduct")
	public ResponseEntity<?> removeCartWithProductId(@RequestBody HashMap<String, String> removeCartRequest) {
		try {
			String keys[] = {"cartId", "userId"};
			if(ShoppingCartConfig.validationWithHashMap(keys, removeCartRequest)) {
				
			}
			List<AddToCart> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")), Long.parseLong(removeCartRequest.get("userId")));
			return ResponseEntity.ok(obj);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/getCart")
	public ResponseEntity<?> getCartByProductId(@RequestBody HashMap<String, String> getCartRequest) {
		try {
			String keys[] = {"userId"};
			if(ShoppingCartConfig.validationWithHashMap(keys, getCartRequest)) {
			}
			List<AddToCart> obj = cartService.getCartByUserId(Long.parseLong(getCartRequest.get("userId")));
			return ResponseEntity.ok(obj);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

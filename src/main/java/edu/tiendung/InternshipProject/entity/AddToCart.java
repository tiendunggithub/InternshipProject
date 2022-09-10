	package edu.tiendung.InternshipProject.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="add_to_cart")
public class AddToCart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
//	@Column(name="prod_id")
//	private Long prod_id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "prod_id")
	Products product;
	
	@Transient
	String productName;
	
	@Column(name="user_id")
	private Long user_id;
	
	@Column(name="qty")
	private int qty;
	
	@Column(name="price")
	private double price;
	
	@Column(name="promotion")
	private double promotion;
	
	@Column(name="adder_date")
	private String adder_date;

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

//	public Long getProd_id() {
//		return prod_id;
//	}
//
//	public void setProd_id(Long prod_id) {
//		this.prod_id = prod_id;
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAdder_date() {
		return adder_date;
	}

	public void setAdder_date(String adder_date) {
		this.adder_date = adder_date;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getProductName() {
		return product.getName();
	}

	public double getPromotion() {
		return promotion;
	}

	public void setPromotion(double promotion) {
		this.promotion = promotion;
	}

	public AddToCart() {
	}

//	public AddToCart(Long id, Long prod_id, Long user_id, int qty, double price, String adder_date) {
//		super();
//		this.id = id;
//		this.prod_id = prod_id;
//		this.user_id = user_id;
//		this.qty = qty;
//		this.price = price;
//		this.adder_date = adder_date;
//	}

	public AddToCart(Long id, Products product, Long user_id, int qty, double price, double promotion, String adder_date) {
		this.id = id;
		this.product = product;
		this.user_id = user_id;
		this.qty = qty;
		this.price = price;
		this.promotion = promotion;
		this.adder_date = adder_date;
	}	
}

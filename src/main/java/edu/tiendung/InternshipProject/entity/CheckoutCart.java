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

@Entity
@Table(name = "check_out_cart")
public class CheckoutCart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="oder_id")
	private String order_id;
	
	@Column(name="user_id")
	private Long user_id;
	
	@Column(name="payment_type")
	private String payment_type;
	
	@Column(name="delivery_address")
	private String delivery_address;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
	Products product;
	
	@Column(name="quantity")
	private int qty;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="oder_date",updatable=false, insertable=true)
	private String oder_date;

	public CheckoutCart() {
	}

	public CheckoutCart(Long id, String order_id, Long user_id, String payment_type, String delivery_address,
			Products product, int qty, Double price, String oder_date) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.user_id = user_id;
		this.payment_type = payment_type;
		this.delivery_address = delivery_address;
		this.product = product;
		this.qty = qty;
		this.price = price;
		this.oder_date = oder_date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String orderId) {
		this.order_id = orderId;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOder_date() {
		return oder_date;
	}

	public void setOder_date(String oder_date) {
		this.oder_date = oder_date;
	}
	
}

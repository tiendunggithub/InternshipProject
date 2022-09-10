package edu.tiendung.InternshipProject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="products")
public class Products {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="price")
	private String price;
	
	@Column(name="promotion")
	private String promotion;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "cate_id")
	private Category category;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "product_Color", 
		joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "color_id", referencedColumnName = "id"))
	List<Color> listColor = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "product_Size", 
		joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "size_id", referencedColumnName = "idSize"))
	List<Size> listSize = new ArrayList<>();
	
//	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Collection<Role> roles;

	public Products() {
	}

	public Products(String name, String image, String price, String promotion, String description, Category category,
		List<Color> listColor, List<Size> listSize) {
		this.name = name;
		this.image = image;
		this.price = price;
		this.promotion = promotion;
		this.description = description;
		this.category = category;
		this.listColor = listColor;
		this.listSize = listSize;
	}

	public Products(Long id, String name, String image, String price, String promotion, String description,
			Category category, List<Color> listColor, List<Size> listSize) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.promotion = promotion;
		this.description = description;
		this.category = category;
		this.listColor = listColor;
		this.listSize = listSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Color> getListColor() {
		return listColor;
	}

	public void setListColor(List<Color> listColor) {
		this.listColor = listColor;
	}

	public List<Size> getListSize() {
		return listSize;
	}

	public void setListSize(List<Size> listSize) {
		this.listSize = listSize;
	}	
}

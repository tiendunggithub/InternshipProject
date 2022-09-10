package edu.tiendung.InternshipProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "size")
public class Size {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idSize")
	private Long id;
	
	@Column(name="size_value")
	private String size;

	public Size() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Size(Long id, String size) {
		super();
		this.id = id;
		this.size = size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	
}

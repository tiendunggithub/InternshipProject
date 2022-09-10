package edu.tiendung.InternshipProject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String Name;
	
//	@OneToMany(mappedBy = "role")
//	private List<Admin> admins;

	public Role() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

//	public List<Admin> getAdmins() {
//		return admins;
//	}
//
//	public void setAdmins(List<Admin> admins) {
//		this.admins = admins;
//	}

	public Role(Long id, String name) {
		this.id = id;
		Name = name;
	}
}

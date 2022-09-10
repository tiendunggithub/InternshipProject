package edu.tiendung.InternshipProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "admins")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="full_name")
	/* @NotEmpty(message = "Tiêu đề không được rỗng") */
	private String name;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="pass")
	private String pass;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "role_id")
	private Role role;

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public Admin() {
	}


	public Admin(Long id, String name, String username, String pass, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.pass = pass;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", username=" + username + ", pass=" + pass + ", role=" + role
				+ "]";
	}	
}

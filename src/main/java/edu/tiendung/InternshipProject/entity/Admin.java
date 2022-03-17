package edu.tiendung.InternshipProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="full_name")
	/* @NotEmpty(message = "Tiêu đề không được rỗng") */
	private String Name;
	
	@Column(name="user_name")
	private String Username;
	
	@Column(name="pass")
	private String Pass;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}
	
	public Admin() {
	}

	public Admin(long id, String name, String username, String pass) {
		this.id = id;
		Name = name;
		Username = username;
		Pass = pass;
	}
	
	public Admin(String name, String username, String pass) {
		Name = name;
		Username = username;
		Pass = pass;
	}
}

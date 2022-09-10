package edu.tiendung.InternshipProject.JwtConfig;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.tiendung.InternshipProject.entity.Customer;

public class UserPrincipal implements UserDetails{
	private Long id;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String email, String password) {
        this.id = id;   
        this.email = email;
        this.password = password;
    }

    public static UserPrincipal create(Customer customer) {
             return new UserPrincipal(
                customer.getId(),
                customer.getEmail(),
                customer.getPassword()              
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail() {
		return email;
	}
}

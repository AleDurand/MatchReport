package project.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class UserModel implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany
	@JoinTable(name = "user_has_authority", 
		joinColumns = {	@JoinColumn(name = "user_id", referencedColumnName = "username") }, 
		inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "id") }
	)
	private Set<Role> authorities;

	public UserModel() {
		authorities = new HashSet<Role>();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}

package mk.polarcape.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mk.polarcape.model.UserAuthority;

@Entity
@Table(name="Employee")
public class Employee implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3171208805971755554L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Size(min=1, max=30)
	private String Name;
	
	@Size(min=1, max=30)
	private String Surname;
	
	@Column(name = "email", nullable = false, unique=true)
	private String email;
	
	private boolean active;
	@Transient
	private long expires;

	@NotNull
	private boolean accountExpired;

	@NotNull
	private boolean accountLocked;

	@NotNull
	private boolean credentialsExpired;

	@NotNull
	private boolean accountEnabled;
//////////////
	@OneToMany(mappedBy = "employee_id")
	private Set<UserAuthority> authorities;
		
	public Employee(String username, Date expires) {
		this.username = username;
		this.expires = expires.getTime();
	}
	public Employee(String username) {
		this.username = username;
	}
	public Employee() {
	}

	@Column(name = "username", nullable = false, unique=true)
	private String username;
	
	@NotEmpty
	private String password;
	
	@OneToMany(mappedBy = "invited_id")
	@JsonIgnore
	private List<Employee_event> invitedGuests;
	
	@OneToMany(mappedBy = "hosting_id")
	@JsonIgnore
	private List<Employee_event> hostingParty;
	
	
	
////////////////////////////////////////////////////////
	///////get set
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}

	//org.springframework.security.crypto.bcrypt.BCrypt
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", Name=" + Name + ", Surname=" + Surname + ", email="
				+ email + ", active=" + active + ", username=" + username + ", password=" + password + "]";
	}

	public List<Employee_event> getInvitedGuests() {
		return invitedGuests;
	}

	public void setInvitedGuests(List<Employee_event> invitedGuests) {
		this.invitedGuests = invitedGuests;
	}

	public List<Employee_event> getHostingParty() {
		return hostingParty;
	}

	public void setHostingParty(List<Employee_event> hostingParty) {
		this.hostingParty = hostingParty;
	}
	
	/////////////////security details
	@JsonIgnore
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}

	// Use Roles as external API
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		if (authorities != null) {
			for (UserAuthority authority : authorities) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		for (UserRole role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(UserRole role) {
		if (authorities == null) {
			authorities = new HashSet<UserAuthority>();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

	
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	
	@JsonIgnore
	public boolean isEnabled() {
		return !accountEnabled;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}
}

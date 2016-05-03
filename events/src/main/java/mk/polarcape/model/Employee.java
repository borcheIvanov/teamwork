package mk.polarcape.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username", nullable = false, unique=true)
	private String username;
	
	@NotEmpty
	private String password;

	@Size(min=1, max=30)
	private String Name;
	
	@Size(min=1, max=30)
	private String Surname;
	
	
	@Column(name = "email", nullable = false, unique=true)
	private String email;
	

	private boolean active;

	 @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "USER_AUTHORITY",
	            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
	            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
	    private List<Authority> authorities;

	 @Column(name = "LASTPASSWORDRESETDATE")
	    @Temporal(TemporalType.TIMESTAMP)
	    @NotNull
	    private Date lastPasswordResetDate;

	  @Column(name = "ENABLED")
	    @NotNull
	    private Boolean enabled;
	    
	@OneToMany(mappedBy = "invited_id")
	@JsonIgnore
	private List<Employee_event> invitedGuests;
	
	@OneToMany(mappedBy = "hosting_id")
	@JsonIgnore
	private List<Employee_event> hostingParty;
	



	public void setId(Long id) {
		this.id = id;
	}

	////////////////////////////////////////////////////////
	///////get set
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}

	//org.springframework.security.crypto.bcrypt.BCrypt
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getIsActive() {
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
	  public List<Authority> getAuthorities() {
	        return authorities;
	    }

	    public void setAuthorities(List<Authority> authorities) {
	        this.authorities = authorities;
	    }

		public Date getLastPasswordResetDate() {
			return lastPasswordResetDate;
		}

		public void setLastPasswordResetDate(Date lastPasswordResetDate) {
			this.lastPasswordResetDate = lastPasswordResetDate;
		}
		public Boolean getEnabled() {
	        return enabled;
	    }

	    public void setEnabled(Boolean enabled) {
	        this.enabled = enabled;
	    }
}

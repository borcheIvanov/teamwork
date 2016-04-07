package mk.polarcape.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Employee")
public class Employee {
	
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

	public void setId(Long id) {
		this.id = id;
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

}

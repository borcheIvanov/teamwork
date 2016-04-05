package mk.polarcape.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name="Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min=1, max=40)
	private String name,surname,email;
	
	private boolean active;
	//////maping
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "hosting")
	private Employee_event hosting;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "invited")
	private List<Employee_event> invited;
	
	/////mapping getters setters
	public Employee_event getHosting() {
		return hosting;
	}

	public void setHosting(Employee_event hosting) {
		this.hosting = hosting;
	}

	public List<Employee_event> getInvited() {
		return invited;
	}

	public void setInvited(List<Employee_event> invited) {
		this.invited = invited;
	}

	///////////////setters and getters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}

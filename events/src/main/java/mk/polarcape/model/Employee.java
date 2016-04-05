package mk.polarcape.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double money;
	
	@Size(min=1, max=30)
	private String Name;
	
	@Size(min=1, max=30)
	private String Surname;
	
	@Size(min=3, max=50)
	private String email;
	
	private boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "invited_id")
	private Event invited;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "hosting_id")
	private Event hosting;
	
	
////////////////////////////////////////////////////////
	///////get set
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	

	public Event getHosting() {
		return hosting;
	}

	public void setHosting(Event hosting) {
		this.hosting = hosting;
	}
	public Event getInvited() {
		return invited;
	}

	public void setInvited(Event invited) {
		this.invited = invited;
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
		return "People [Name=" + Name + ", Surname=" + Surname + ", email=" + email + ", active=" + active + ", invited="
				+ invited + ", hosting=" + hosting + ", id=" + id + ", money=" + money + "]";
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	

	


}

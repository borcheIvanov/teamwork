package mk.polarcape.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double budget;
	
	@ManyToMany(mappedBy = "invited")
	@JsonIgnore
	private List<Employee> invitedGuests;
	
	@ManyToMany(mappedBy = "hosting")
	@JsonIgnore
	private List<Employee> hostingParty;

	public List<Employee> getHostingParty() {
		return hostingParty;
	}

	public void setHostingParty(List<Employee> hostingParty) {
		this.hostingParty = hostingParty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Events [naem=" + name + ", budget=" + budget + ", invitedGuests=" + invitedGuests + ", hostingParty="
				+ hostingParty + ", id=" + id + "]";
	}

	public List<Employee> getInvitedGuests() {
		return invitedGuests;
	}

	public void setInvitedGuests(List<Employee> invitedGuests) {
		this.invitedGuests = invitedGuests;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

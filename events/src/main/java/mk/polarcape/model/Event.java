package mk.polarcape.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double budget;
	
	@OneToMany(mappedBy = "events_id")
	@JsonIgnore
	private List<Employee_event> events_id;
	
	 
	    @NotNull
	    private Date createdDate= new Date();
	    
	    private Date expirationDate;
	    
	    private String createdBy;
/////////////////////////////
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate){
		this.expirationDate = expirationDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", budget=" + budget + ", events_id=" + events_id
				+ ", createdDate=" + createdDate + ", expirationDate=" + expirationDate + ", createdBy=" + createdBy
				+ "]";
	}

	

	public List<Employee_event> getEvents_id() {
		return events_id;
	}

	public void setEvents_id(List<Employee_event> events_id) {
		this.events_id = events_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

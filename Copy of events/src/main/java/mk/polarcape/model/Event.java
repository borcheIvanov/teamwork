package mk.polarcape.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String Event_name;
	private double budget;
	@Temporal(TemporalType.DATE)
	private Date creation_date;
	private Date end_date;
	
	
	///////////////////GETS SETS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEvent_name() {
		return Event_name;
	}
	public void setEvent_name(String event_name) {
		Event_name = event_name;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	

}

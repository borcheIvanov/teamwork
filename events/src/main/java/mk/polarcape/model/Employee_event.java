package mk.polarcape.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_event")
public class Employee_event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "invited_id")
	private Employee invited_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hosting_id")
	private Employee hosting_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "events_id")
	private Event events_id;
	
	private double moneyOWNED;
	
////////////////////
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getInvited_id() {
		return invited_id;
	}

	public void setInvited_id(Employee invited_id) {
		this.invited_id = invited_id;
	}

	public Employee getHosting_id() {
		return hosting_id;
	}

	public void setHosting_id(Employee hosting_id) {
		this.hosting_id = hosting_id;
	}

	public Event getEvents_id() {
		return events_id;
	}

	public void setEvents_id(Event events_id) {
		this.events_id = events_id;
	}

	public double getMoneyOWNED() {
		return moneyOWNED;
	}

	public void setMoneyOWNED(double moneyOWNED) {
		this.moneyOWNED = moneyOWNED;
	}
	
}

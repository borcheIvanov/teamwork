<<<<<<< HEAD
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
	private Employee invited;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hosting_id")
	private Employee hosting;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "events_id")
	private Event events;
	
	private double moneyOWNED;
	
////////////////////
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getInvited() {
		return invited;
	}

	public void setInvited(Employee invited) {
		this.invited = invited;
	}

	public Employee getHosting() {
		return hosting;
	}

	public void setHosting(Employee hosting) {
		this.hosting = hosting;
	}

	public Event getEvents() {
		return events;
	}

	public void setEvents(Event events) {
		this.events = events;
	}

	public double getMoneyOWNED() {
		return moneyOWNED;
	}

	public void setMoneyOWNED(double moneyOWNED) {
		this.moneyOWNED = moneyOWNED;
	}
	
}
=======
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
	private Employee invited;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hosting_id")
	private Employee hosting;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "events_id")
	private Event events;
	
	private double moneyOWNED;
	
////////////////////
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getInvited() {
		return invited;
	}

	public void setInvited(Employee invited) {
		this.invited = invited;
	}

	public Employee getHosting() {
		return hosting;
	}

	public void setHosting(Employee hosting) {
		this.hosting = hosting;
	}

	public Event getEvents() {
		return events;
	}

	public void setEvents(Event events) {
		this.events = events;
	}

	public double getMoneyOWNED() {
		return moneyOWNED;
	}

	public void setMoneyOWNED(double moneyOWNED) {
		this.moneyOWNED = moneyOWNED;
	}
	
}
>>>>>>> refs/heads/milos

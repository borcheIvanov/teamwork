package mk.polarcape.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Employee_event")
public class Employee_event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "invited_id")
	private Employee invited;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hosting_id")
	private Employee hosting;
}

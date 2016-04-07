<<<<<<< HEAD
package mk.polarcape.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.polarcape.model.Employee_event;
import mk.polarcape.service.Employee_eventService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Employee_eventController {
	@Autowired
	private Employee_eventService Employee_eventService;

	@RequestMapping(value = "/empevent", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee_event> getEmployee_events() {
		return Employee_eventService.findAll();
	}

	@RequestMapping(value = "/Empevent/{id}", method = RequestMethod.GET)
	public Employee_event getEmployee_eventById(@PathVariable Long id) {
		return Employee_eventService.findById(id);
	}

	@RequestMapping(value="/Empevent", method = RequestMethod.POST)
	@ResponseBody
	public Employee_event createEmployee_event(@RequestBody Employee_event Employee_event){
		return Employee_eventService.save(Employee_event);
	}
	

	@RequestMapping(value="/Empevent/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event updateEmployee_event(@PathVariable Long id, @RequestBody Employee_event Employee_event){
		Employee_event currentEmployee_event = Employee_eventService.findById(id);
		
		currentEmployee_event.setHosting(Employee_event.getHosting());
		currentEmployee_event.setInvited(Employee_event.getInvited());
		currentEmployee_event.setEvents(Employee_event.getEvents());
		currentEmployee_event.setMoneyOWNED(Employee_event.getMoneyOWNED());
		
		return Employee_eventService.save(currentEmployee_event);
	}
	
	@RequestMapping(value="/Empevent/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteEmployee_event(@PathVariable Long id){
		return Employee_eventService.delete(id);
}
}
=======
package mk.polarcape.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.polarcape.model.Employee_event;
import mk.polarcape.service.Employee_eventService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Employee_eventController {
	@Autowired
	private Employee_eventService Employee_eventService;

	@RequestMapping(value = "/empevent", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee_event> getEmployee_events() {
		return Employee_eventService.findAll();
	}

	@RequestMapping(value = "/empevent/{id}", method = RequestMethod.GET)
	public Employee_event getEmployee_eventById(@PathVariable Long id) {
		return Employee_eventService.findById(id);
	}
	////////////////////spec
	@RequestMapping(value = "/empevent/{invited_id}/{events_id}", method = RequestMethod.GET)
	public List<Employee_event> getEmployee_eventInvited(@PathVariable Long invited_id,@PathVariable Long events_id) {
		return Employee_eventService.selectInvited(invited_id, events_id);
	}

	@RequestMapping(value="/empevent", method = RequestMethod.POST)
	@ResponseBody
	public Employee_event createEmployee_event(@RequestBody Employee_event Employee_event){
		return Employee_eventService.save(Employee_event);
	}
	

	@RequestMapping(value="/empevent/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event updateEmployee_event(@PathVariable Long id, @RequestBody Employee_event Employee_event){
		Employee_event currentEmployee_event = Employee_eventService.findById(id);
		
		currentEmployee_event.setHosting(Employee_event.getHosting());
		currentEmployee_event.setInvited(Employee_event.getInvited());
		currentEmployee_event.setEvents(Employee_event.getEvents());
		currentEmployee_event.setMoneyOWNED(Employee_event.getMoneyOWNED());
		
		return Employee_eventService.save(currentEmployee_event);
	}
	
	@RequestMapping(value="/empevent/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteEmployee_event(@PathVariable Long id){
		return Employee_eventService.delete(id);
}
}
>>>>>>> refs/heads/milos

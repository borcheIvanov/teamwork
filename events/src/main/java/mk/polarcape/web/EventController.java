package mk.polarcape.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.polarcape.model.Event;
import mk.polarcape.model.Employee;
import mk.polarcape.service.EventService;
import mk.polarcape.service.EmployeeService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EmployeeService employeeService;

	

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getEventss() {
		return eventService.findAll();
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Event getEventsById(@PathVariable Long id) {
		return eventService.findById(id);
	}

	@RequestMapping(value = "/events", method = RequestMethod.POST)
	@ResponseBody
	public Event createEvents(@RequestBody Event event) {
		return eventService.save(event);
	}

	@RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Event updateEvents(@PathVariable Long id, @RequestBody Event event) {
		Event currentEvents = eventService.findById(id);
		
		currentEvents.setName(event.getName());
		currentEvents.setBudget(event.getBudget());
		
		return eventService.save(currentEvents);
	}
	
	@RequestMapping(value="/events/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteEvents(@PathVariable Long id){
		return eventService.delete(id);
	}

	@RequestMapping(value="/events/{id}/invitedPeoples", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Employee> getInvitedPeoples(@PathVariable Long id){
		return employeeService.getInvitedEmployeeForEventId(id);
	}
}

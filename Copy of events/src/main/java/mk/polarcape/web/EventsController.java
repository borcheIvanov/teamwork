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

import mk.polarcape.model.Events;
import mk.polarcape.model.People;
import mk.polarcape.service.EventsService;
import mk.polarcape.service.PeopleService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventsController {
	
	@Autowired
	private EventsService eventsService;
	
	@Autowired
	private PeopleService peopleService;

	

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	@ResponseBody
	public List<Events> getEventss() {
		return eventsService.findAll();
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Events getEventsById(@PathVariable Long id) {
		return eventsService.findById(id);
	}

	@RequestMapping(value = "/events", method = RequestMethod.POST)
	@ResponseBody
	public Events createEvents(@RequestBody Events events) {
		return eventsService.save(events);
	}

	@RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Events updateEvents(@PathVariable Long id, @RequestBody Events events) {
		Events currentEvents = eventsService.findById(id);
		
		currentEvents.setName(events.getName());
		currentEvents.setBudget(events.getBudget());
		
		return eventsService.save(currentEvents);
	}
	
	@RequestMapping(value="/events/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteEvents(@PathVariable Long id){
		return eventsService.delete(id);
	}

	@RequestMapping(value="/events/{id}/invitedPeoples", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<People> getInvitedPeoples(@PathVariable Long id){
		return peopleService.getInvitedPeoplesForEventsId(id);
	}
}

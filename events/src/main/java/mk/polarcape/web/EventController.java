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
import mk.polarcape.model.Event;
import mk.polarcape.service.Employee_eventService;
import mk.polarcape.service.EventService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
	
	@Autowired
	private EventService eventService;
	@Autowired
	private Employee_eventService empService;
	
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getEventss() {
		return eventService.findAll();
	}
	@RequestMapping(value = "/eventactive", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getEventActive() {
		return eventService.findActive();
	}
	@RequestMapping(value = "/eventclosed", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getEventClosed() {
		return eventService.findClosed();
	}
	
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Event getEventsById(@PathVariable Long id) {
		return eventService.findById(id);
	}
	

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	@ResponseBody
	public Event createEvents(@RequestBody Event event) {
		event.setArchived(false);//otvoren e eventot
		
		return eventService.save(event);
	}
//////////pri promena na cena ako platil vidi..
	@RequestMapping(value = "/event/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Event updateEvents(@PathVariable Long id, @RequestBody Event event) {
		Event currentEvents = eventService.findById(id);
		if(event.getName()!=null)
		currentEvents.setName(event.getName());
		if(event.getBudget()!=0){
		currentEvents.setBudget(event.getBudget());
		List<Employee_event> emp = empService.selectInvited(id);
		int size=emp.size();
		for(Employee_event e:emp){
			if(e.getIsPayed()==true){
			e.setMoneyOWNED(event.getBudget()/size-e.getMoneyOWNED());
			e.setPayed(false);
			}
			else 
				e.setMoneyOWNED(event.getBudget()/size);
		}
		}
		return eventService.save(currentEvents);
	}
	
	@RequestMapping(value="/event/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteEvents(@PathVariable Long id){
		return eventService.delete(id);
	}
}

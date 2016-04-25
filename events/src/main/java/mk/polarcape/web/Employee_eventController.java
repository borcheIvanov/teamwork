package mk.polarcape.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.polarcape.model.Employee_event;
import mk.polarcape.model.Event;
import mk.polarcape.service.Employee_eventService;
import mk.polarcape.service.EventService;
import mk.polarcape.service.impl.MailNotifier;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Employee_eventController {
	@Autowired
	private Employee_eventService Employee_eventService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired  MailNotifier MailNotifier;
	
	///scheduled task every day check people that didnt pay 1 day prior expiration and close events
	public void MailNotifier(){
		//close event
		MailNotifier.mailNotifier();
		//System.out.println("mail notifier");
	};


	@RequestMapping(value = "/empevent", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee_event> getEmployee_events() {
		return Employee_eventService.findAll();
	}

	@RequestMapping(value = "/empevent/{id}", method = RequestMethod.GET)
	public Employee_event getEmployee_eventById(@PathVariable Long id) {
		//archiveCheck();
		return Employee_eventService.findById(id);
	}
	////////////////////specialni
	@RequestMapping(value = "/empeventinv/{events_id}", method = RequestMethod.GET)
	public List<Employee_event> getEmployee_eventInvited(@PathVariable Long events_id) {
		return Employee_eventService.selectInvited(events_id);
	}
	@RequestMapping(value = "/empeventhost/{hosting_id}", method = RequestMethod.GET)
	public List<Employee_event> getEmployee_eventHosting(@PathVariable Long hosting_id) {
		return Employee_eventService.selectHosting(hosting_id);
	}
	@RequestMapping(value = "/empeventwhere/{invited_id}", method = RequestMethod.GET)
	public List<Employee_event> getEmployee_eventWhere(@PathVariable Long invited_id) {
		return Employee_eventService.selectWhere(invited_id);
	}
	@RequestMapping(value="/empevent", method = RequestMethod.POST)
	@ResponseBody
	public Employee_event createEmployee_event(@RequestBody Employee_event Employee_event,@RequestParam String selected){
		Integer c=Integer.parseInt(selected);
		Employee_event.setMoneyOWNED(Employee_event.getEvents_id().getBudget()/c);//selected e broj na povikani useri
		Employee_event.setFlag(false);
		return Employee_eventService.save(Employee_event);
	}
	

	@RequestMapping(value="/empevent/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event updateEmployee_event(@PathVariable Long id, @RequestBody Employee_event Employee_event){
		Employee_event currentEmployee_event = Employee_eventService.findById(id);
		
		currentEmployee_event.setHosting_id(Employee_event.getHosting_id());
		currentEmployee_event.setInvited_id(Employee_event.getInvited_id());
		currentEmployee_event.setEvents_id(Employee_event.getEvents_id());
		currentEmployee_event.setFlag(Employee_event.isFlag());//platilt
		currentEmployee_event.setMoneyOWNED(Employee_event.getMoneyOWNED());
		archiveCheck();
		
		return Employee_eventService.save(currentEmployee_event);
	}
	//NOTIFIKACIJA DEKA PLATIL mora nov query
	@RequestMapping(value="/empeventNOTIFY/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event notifyEmployee_event(@PathVariable Long id, @RequestBody Employee_event Employee_event){
		Employee_event ce = Employee_eventService.findById(id);
		if(ce.isFlag()==false&&ce.getMoneyOWNED()!=0)
		ce.setFlag(true);//platilts
		return Employee_eventService.save(ce);
}
	//
	@RequestMapping(value="/empeventAPROVE/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event aproveEmployee_event(@PathVariable Long id, @RequestBody Employee_event Employee_event){
		Employee_event ce = Employee_eventService.findById(id);
		if(ce.isFlag()==true&&ce.getMoneyOWNED()!=0)
		ce.setFlag(false);//platilts
		ce.setMoneyOWNED(0);
		return Employee_eventService.save(ce);
}

	/// if all payed archive event DA SE PROVERI 
	private void archiveCheck() {
		List<Event> ev = eventService.findActive();//site kade getArchived(true)
		for(Event e:ev){
		List<Employee_event> ee = Employee_eventService.selectInvited(e.getId());//site povikani na eventot
		boolean allpay=false;
		int counter=0;
		int size=ee.size();
		for(Employee_event s:ee){//ako brojot na site sto platitle e ist so golemina na nizata stavi vo arhiva eventot
			if(s.getMoneyOWNED()==0)
				counter++;
			if(counter==size)
				allpay=true;
		}
		if(allpay){
			e.setArchived(false);//na 1
		}
	}
	}
	

	@RequestMapping(value="/empevent/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteEmployee_event(@PathVariable Long id){
		return Employee_eventService.delete(id);
	}
}


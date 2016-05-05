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
	@RequestMapping(value = "/empeventNOTPAYED/{events_id}", method = RequestMethod.GET)
	public List<Employee_event> getEmployee_eventnotpayed(@PathVariable Long events_id) {
		return Employee_eventService.InvitedNotPayed(events_id);
	}
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
	
	//promena 
	@RequestMapping(value="/empevent", method = RequestMethod.POST)
	@ResponseBody
	public List<Employee_event> createEmployee_event(@RequestBody List<Employee_event> Es){
	/*	Gson gson = new Gson();//ako se praka string
		List<Employee_event> Es= gson.fromJson(Ees, new TypeToken<List<Employee_event>>(){}.getType());*/
		int c=Es.size();
		double cash= Es.get(0).getEvents_id().getBudget();
		/*System.out.println(c +" size and money "+ cash);
		System.out.println(Ees);
		System.out.println(cash/c);*/
		for(Employee_event e:Es){
		e.setMoneyOWNED(cash/c);        //selected e broj na povikani useri
		e.setFlag(false);
		e.setPayed(false);
		System.out.println(e);
		Employee_eventService.save(e);
	}
		for(Employee_event e:Es)
		Employee_eventService.inviteMail(e);
		
		return Es;
	}
	

	@RequestMapping(value="/empevent/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event updateEmployee_event(@PathVariable Long id, @RequestBody Employee_event Employee_event){
		Employee_event currentEmployee_event = Employee_eventService.findById(id);
		if(Employee_event.getHosting_id()!=null)
		currentEmployee_event.setHosting_id(Employee_event.getHosting_id());
		if(Employee_event.getInvited_id()!=null)
		currentEmployee_event.setInvited_id(Employee_event.getInvited_id());
		if(Employee_event.getEvents_id()!=null)
		currentEmployee_event.setEvents_id(Employee_event.getEvents_id());
		currentEmployee_event.setPayed(Employee_event.getIsPayed());
		Employee_eventService.save(currentEmployee_event);
		archiveCheck();
		return currentEmployee_event;
	}
	//NOTIFIKACIJA DEKA PLATIL
	@RequestMapping(value="/empeventNOTIFY/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event notifyEmployee_event(@PathVariable Long id){
		
		Employee_event ce = Employee_eventService.findById(id);
		//System.out.println(ce.getIsFlag() +" and "+ce.getMoneyOWNED());
		if(ce.getIsFlag()==false&&ce.getIsPayed()==false)
		ce.setFlag(true);//platil
		return Employee_eventService.save(ce);
}
	//NOTIFIKACIJA MAIL do site sto ne platile za daden event
		@RequestMapping(value="/mail/notify/{id}", method = RequestMethod.PUT)
		@ResponseBody
		public void mailnotifyEmployee_event(@PathVariable Long id){
			Employee_eventService.notifyMail(id);
	}
		//MAIL DEKA PLATIL
		@RequestMapping(value="/mail/payed/{id}", method = RequestMethod.PUT)
		@ResponseBody
		public void mailpayedEmployee_event(@PathVariable Long id){
			Employee_eventService.payedMail(id);
	}
		
	//
	@RequestMapping(value="/empeventAPROVE/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee_event aproveEmployee_event(@PathVariable Long id){
		Employee_event ce = Employee_eventService.findById(id);
		if(ce.getIsFlag()==true){
		ce.setFlag(false);//platil
		ce.setPayed(true);
		Employee_eventService.save(ce);
		archiveCheck();
		}
		return ce;
}
	

	/// if all payed archive event (proverka na site neplateni eventi)
	private void archiveCheck() {
		List<Event> ev = eventService.findActive();//site kade getArchived(false) aktivni
		for(Event e:ev){
		List<Employee_event> ee = Employee_eventService.selectInvited(e.getId());//site povikani na eventot
		boolean allpay=false;
		int counter=0;
		int size=ee.size();
		//System.out.println(size);
		for(Employee_event s:ee){//ako brojot na site sto platitle e ist so golemina na nizata stavi vo arhiva eventot
			if(s.getIsPayed()==true)
				counter++;
		}
		//System.out.println(counter);
		if(counter==size)
			allpay=true;
		//System.out.println(allpay);
		if(allpay==true){
			e.setArchived(true);
		eventService.save(e);
		}
	}
	}
	

	@RequestMapping(value="/empevent/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteEmployee_event(@PathVariable Long id){
		return Employee_eventService.delete(id);
	}
}


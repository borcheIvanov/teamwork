package mk.polarcape.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import mk.polarcape.model.Employee;
import mk.polarcape.model.Employee_event;
import mk.polarcape.model.Event;
import mk.polarcape.repository.EmployeeRepository;
import mk.polarcape.repository.Employee_eventRepository;
import mk.polarcape.repository.EventRepository;
import mk.polarcape.service.Employee_eventService;

@Service
public class Employee_eventServiceImpl implements Employee_eventService {
	@Autowired
	private Employee_eventRepository Employee_eventRepository;
	@Autowired
	private EmployeeRepository EmployeeRepository;
	@Autowired
	private EventRepository EventRepository;
	
	@Autowired
    private  JavaMailSender  javaMailService;
	
	public Employee_event findById(Long id) {
		return Employee_eventRepository.findById(id);
	}

	public List<Employee_event> findAll() {
		return Employee_eventRepository.findAll();
	}

	public Employee_event save(Employee_event Employee_event) {
		//get needed event and employees
	         Event evn = EventRepository.findById(Employee_event.getEvents_id().getId());
	         Employee emp = EmployeeRepository.findById(Employee_event.getInvited_id().getId());
	         Employee emph = EmployeeRepository.findById(Employee_event.getHosting_id().getId());
	        SimpleMailMessage mailMessage=new SimpleMailMessage();
	          try{     
	       mailMessage.setTo(emp.getEmail());
	       mailMessage.setFrom("polarcape@outlook.com");
	        mailMessage.setSubject("Event");
	        mailMessage.setText("Dear " +emp.getName() +"\n "+emp.getEmail() + "\n You have been invited to attend the event: "
	        		+ evn.getName() + ". Event created by:" + emph.getName() + ".\n"
	        				+ "Money for the event is "+ Employee_event.getMoneyOWNED() + "\n Have a nice time !!!" );
	        
	        System.out.println("Dear " +emp.getName() +"\n "+emp.getEmail() +"\n You have been invited to attend the event: "
	        		+ evn.getName() + " created by: " + emp.getName() + "\n"
    				+ " money for the event is "+ Employee_event.getMoneyOWNED() );
	        javaMailService.send(mailMessage);
	          }
	          catch(Exception e){
	        	  System.out.println("greska");
	        	  System.out.println(e);
	        	  
	          }
	        return Employee_eventRepository.save(Employee_event);
	}

	public int delete(Long id) {
		return Employee_eventRepository.delete(id);
	}
	public List<Employee_event> selectInvited(Long events_id){
		return Employee_eventRepository.selectInvited(events_id);
	}
	public List<Employee_event> selectHosting(Long hosting_id){
		return Employee_eventRepository.selectHosting(hosting_id);
	}

	public List<Employee_event> selectWhere(Long invited_id) {
		return Employee_eventRepository.selectWhere(invited_id);
	
	}
	
	///send mail to people who didnt pay 1 day prior expiration date
	public void mailNotifier(){
		Date cd =new Date();//datum current
		List<Event> evn = EventRepository.findAll();
		for(Event ev:evn){
			Date dayBefore = new Date(ev.getExpirationDate().getTime() - 24*3600*1000);
			if(cd.compareTo(dayBefore)>=0){
			List<Employee_event> empev = Employee_eventRepository.selectInvited(ev.getId());
			for(Employee_event ee:empev){
				if(ee.getMoneyOWNED()>0){
       SimpleMailMessage mailMessage=new SimpleMailMessage();
         try{     
      mailMessage.setTo(ee.getInvited_id().getEmail());
      mailMessage.setFrom("polarcape@outlook.com");
       mailMessage.setSubject("Event");
       mailMessage.setText("Dear " +ee.getInvited_id().getName() +"\n "+ee.getInvited_id().getEmail() + "\n You have to pay for the event: "
       		+ ev.getName() + ". Event created by:" + ev.getCreatedBy() + ".\n"
       				+ " Expiration date is in 1 day. Money to pay " + ee.getMoneyOWNED() );
       javaMailService.send(mailMessage);
       System.out.println("Dear " +ee.getInvited_id().getName() +"\n "+ee.getInvited_id().getEmail() + "\n You have to pay for the event: "
          		+ ev.getName() + ". Event created by:" + ev.getCreatedBy() + ".\n"
   				+ " Expiration date is in 1 day. Money to pay " + ee.getMoneyOWNED() );
         }
         catch(Exception e){
       	  System.out.println("greska");
       	  System.out.println(e);
         }
				}//if money
				}//for ee
				}// if exp date
				}// for ev
	}
}

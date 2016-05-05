package mk.polarcape.service.impl;

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
	public List<Employee_event> InvitedNotPayed(Long events_id){
		return Employee_eventRepository.InvitedNotPayed(events_id);
	}
	public Employee_event save(Employee_event Employee_event) {
		//get needed event and employees stavi IF ako money e 0
	        return Employee_eventRepository.save(Employee_event);
	}
	public void inviteMail(Employee_event Employee_event){
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
        				+ "Money for the event is "+ Employee_event.getMoneyOWNED() + "\n\n Sincerely,\n Polarcape team" );
          
   /*     System.out.println("Dear " +emp.getName() +"\n "+emp.getEmail() +"\n You have been invited to attend the event: "
        		+ evn.getName() + " created by: " + emp.getName() + "\n"
				+ " money for the event is "+ Employee_event.getMoneyOWNED() );*/
        javaMailService.send(mailMessage);
          }
          catch(Exception e){
        	  System.out.println("greska");
        	  System.out.println(e);
        	  
          }
	}
	public void payedMail(Long id){
		 List<Employee_event> emp = Employee_eventRepository.selectInvited(id);
	        SimpleMailMessage mailMessage=new SimpleMailMessage();
	        for(Employee_event e:emp){    
	        	  if(e.getIsPayed()==true){//ako platil
	       mailMessage.setTo(e.getInvited_id().getEmail());
	       mailMessage.setFrom("polarcape@outlook.com");
	        mailMessage.setSubject("Event paid");
	        mailMessage.setText("Dear " +e.getInvited_id().getName() +"\n "+e.getInvited_id().getEmail() + "\n This is a notification that you paid for the event: "
	        		+ e.getEvents_id().getName() + ". Event created by:" + e.getHosting_id().getName() + ".\n"
	        				+ "Money payed "+ e.getMoneyOWNED() + "\n\n Sincerely,\n Polarcape team" );
	        	  javaMailService.send(mailMessage);}
	        	  }
	}
	public void notifyMail(Long id){
	  List<Employee_event> emp = Employee_eventRepository.selectInvited(id);
	        SimpleMailMessage mailMessage=new SimpleMailMessage();
	        for(Employee_event e:emp){    
	        	  if(e.getIsPayed()==false){//ako ne platil
	       mailMessage.setTo(e.getInvited_id().getEmail());
	       mailMessage.setFrom("polarcape@outlook.com");
	        mailMessage.setSubject("Event to pay");
	        mailMessage.setText("Dear " +e.getInvited_id().getName() +"\n "+e.getInvited_id().getEmail() + "\n This is a notification to pay for the event: "
	        		+ e.getEvents_id().getName() + ". Event created by:" + e.getHosting_id().getName() + ".\n"
	        				+ "Money for the event is "+ e.getMoneyOWNED() + "\n\n Sincerely,\n Polarcape team" );
	        	  javaMailService.send(mailMessage);}
	        	  }
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
	
}

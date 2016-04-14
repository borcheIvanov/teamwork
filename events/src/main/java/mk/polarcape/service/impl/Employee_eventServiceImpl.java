package mk.polarcape.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import mk.polarcape.model.Employee;
import mk.polarcape.model.Employee_event;
import mk.polarcape.model.Event;
import mk.polarcape.repository.Employee_eventRepository;
import mk.polarcape.service.Employee_eventService;
import mk.polarcape.repository.EmployeeRepository;
import mk.polarcape.repository.EventRepository;

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
	         Event evn = EventRepository.findById(Employee_event.getEvents_id().getId());
	         Employee emp = EmployeeRepository.findById(Employee_event.getInvited_id().getId());
	         Employee emph = EmployeeRepository.findById(Employee_event.getHosting_id().getId());
	        SimpleMailMessage mailMessage=new SimpleMailMessage();
	          try{     
	       mailMessage.setTo(emp.getEmail());
	       mailMessage.setFrom("milospolarcape@outlook.com");
	        mailMessage.setSubject("Event");
	        mailMessage.setText("Dear " +emp.getName() +"\n "+emp.getEmail() + "\n You have been invited to attend the event: "
	        		+ evn.getName() + " created by:" + emph.getName() + "\n"
	        				+ "money for the event is "+ Employee_event.getMoneyOWNED() );
	        
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

}

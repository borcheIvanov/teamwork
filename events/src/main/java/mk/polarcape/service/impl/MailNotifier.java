package mk.polarcape.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import mk.polarcape.model.Employee_event;
import mk.polarcape.model.Event;
import mk.polarcape.repository.Employee_eventRepository;
import mk.polarcape.repository.EventRepository;

@EnableScheduling
@Configuration
public class MailNotifier {
	@Autowired
	private Employee_eventRepository Employee_eventRepository;

	@Autowired
    private  JavaMailSender  javaMailService;
	@Autowired
	private EventRepository EventRepository;
	///send mail to people who didnt pay 1 day prior expiration date
		@Scheduled(fixedRate=24*60*60*1000)
		public void mailNotifier(){
			Date cd =new Date();//date current
			List<Event> evn = EventRepository.findAll();
			for(Event ev:evn){
				Date dayBefore = new Date(ev.getExpirationDate().getTime() - 24*3600*1000);	
				if(cd.compareTo(dayBefore)>=0){
				List<Employee_event> empev = Employee_eventRepository.selectInvited(ev.getId());
				for(Employee_event ee:empev){
					if(ee.getIsPayed()==false){
	       SimpleMailMessage mailMessage=new SimpleMailMessage();
	         try{     
	      mailMessage.setTo(ee.getInvited_id().getEmail());
	      mailMessage.setFrom("polarcape@outlook.com");
	       mailMessage.setSubject("Expiraton date to pay for event");
	       mailMessage.setText("Dear " +ee.getInvited_id().getName() +"\n "+ee.getInvited_id().getEmail() + "\n You have to pay for the event: "
	       		+ ev.getName() + ". Event created by:" + ee.getHosting_id().getName() + ".\n"
	       				+ " Expiration date is less then 1 day. Money to pay " + ee.getMoneyOWNED() );
	       javaMailService.send(mailMessage);
	       
	      /* System.out.println("Dear " +ee.getInvited_id().getName() +"\n "+ee.getInvited_id().getEmail() + "\n You have to pay for the event: "
	          		+ ev.getName() + ". Event created by:" + ee.getHosting_id().getName() + ".\n"
	   				+ " Expiration date is in less than 1 day. Money to pay " + ee.getMoneyOWNED() );*/
	         }
	         catch(Exception e){
	       	  //System.out.println("greska");
	       	  System.out.println(e);
	         }
					}//if money
					}//for ee
					}// if exp date
					}// for ev
		}
}

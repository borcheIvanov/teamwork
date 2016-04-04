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

import mk.polarcape.model.People;
import mk.polarcape.service.PeopleService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PeopleController {
	@Autowired
	private PeopleService peopleService;

	@RequestMapping(value = "/people", method = RequestMethod.GET)
	@ResponseBody
	public List<People> getPeoples() {
		return peopleService.findAll();
	}

	@RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
	public People getPeopleById(@PathVariable Long id) {
		return peopleService.findById(id);
	}

	@RequestMapping(value="/people", method = RequestMethod.POST)
	@ResponseBody
	public People createPeople(@RequestBody People people){
		return peopleService.save(people);
	}

	@RequestMapping(value="/people/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public People updatePeople(@PathVariable Long id, @RequestBody People people){
		People currentPeople = peopleService.findById(id);
		
		currentPeople.setName(people.getName());
		currentPeople.setSurname(people.getSurname());
		currentPeople.setEmail(people.getEmail());
		currentPeople.setPass(people.getPass());
		currentPeople.setMoney(people.getMoney());
		currentPeople.setInvited(people.getInvited());
		currentPeople.setHosting(people.getHosting());
		
		return peopleService.save(currentPeople);
	}
	
	@RequestMapping(value="/people/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deletePeople(@PathVariable Long id){
		return peopleService.delete(id);
	}
	@RequestMapping(value = "/login/{email}/{pass}", method = RequestMethod.GET)
	@ResponseBody
	public People login(@PathVariable String email, @PathVariable String pass) {
		return peopleService.login(email, pass);
	}
	
}

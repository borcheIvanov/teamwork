package mk.polarcape.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.polarcape.model.People;
import mk.polarcape.repository.PeopleRepository;
import mk.polarcape.service.PeopleService;


@Service
public class PeopleServiceImpl implements PeopleService{

	@Autowired
	private PeopleRepository peopleRepository;
	
	public People findById(Long id) {
		return peopleRepository.findById(id);
	}

	public List<People> findAll() {
		return peopleRepository.findAll();
	}

	public People save(People people) {
		return peopleRepository.save(people);
	}

	public int delete(Long id) {
		return peopleRepository.delete(id);
	}

	public ArrayList<People> getInvitedPeoplesForEventsId(Long id) {
		return (ArrayList<People>) peopleRepository.getInvitedPeoplesForEventsId(id);
	}
	
	
	public People login(String email, String pass) {
		return peopleRepository.login(email, pass);
	}
}

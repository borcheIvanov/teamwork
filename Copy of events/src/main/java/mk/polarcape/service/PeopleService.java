package mk.polarcape.service;

import java.util.ArrayList;
import java.util.List;

import mk.polarcape.model.People;

public interface PeopleService {
	People findById(Long id);
	List<People> findAll();
	People save(People task);
	int delete(Long id);
	ArrayList<People> getInvitedPeoplesForEventsId(Long id);
	People login(String email, String pass);
}

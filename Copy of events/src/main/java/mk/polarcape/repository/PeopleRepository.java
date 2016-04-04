package mk.polarcape.repository;

import java.util.List;

import mk.polarcape.model.People;

public interface PeopleRepository {
	People findById(Long id);
	List<People> findAll();
	People save(People task);
	int delete(Long id);
	List<People> getInvitedPeoplesForEventsId(Long id);
	People login(String email, String pass);

}

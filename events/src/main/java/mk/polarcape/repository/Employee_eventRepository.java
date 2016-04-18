package mk.polarcape.repository;

import java.util.List;

import mk.polarcape.model.Employee_event;

public interface Employee_eventRepository {

	Employee_event findById(Long id);
	List<Employee_event> findAll();
	Employee_event save(Employee_event task);
	int delete(Long id);
	///
	public List<Employee_event> selectInvited(Long events_id);
	public List<Employee_event> selectHosting(Long hosting_id);
	public List<Employee_event> selectWhere(Long invited_id);
	
}

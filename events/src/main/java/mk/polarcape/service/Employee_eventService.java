package mk.polarcape.service;

import java.util.List;

import mk.polarcape.model.Employee_event;

public interface Employee_eventService {
	Employee_event findById(Long id);
	List<Employee_event> findAll();
	Employee_event save(Employee_event task);
	int delete(Long id);
	///other
	public List<Employee_event> selectInvited(Long invited_id,Long events_id);
}

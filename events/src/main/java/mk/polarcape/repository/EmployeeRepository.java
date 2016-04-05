package mk.polarcape.repository;

import java.util.List;

import mk.polarcape.model.Employee;

public interface EmployeeRepository {
	Employee findById(Long id);
	List<Employee> findAll();
	Employee save(Employee task);
	int delete(Long id);
	List<Employee> getInvitedEmployeeForEventId(Long id);
	Employee login(String username, String pass);
	
}

package mk.polarcape.service;

import java.util.List;

import mk.polarcape.model.Employee;

public interface EmployeeService {
	Employee findById(Long id);
	Employee findByUsername(String username);
	Employee findByEmail(String email);
	List<Employee> findAll();
	Employee save(Employee task);
	int delete(Long id);
	Employee login(String username, String pass);
}

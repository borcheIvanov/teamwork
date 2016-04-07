package mk.polarcape.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.polarcape.model.Employee;
import mk.polarcape.repository.EmployeeRepository;
import mk.polarcape.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee findById(Long id) {
		return employeeRepository.findById(id);
	}
	public Employee findByUsername(String username) {
		return employeeRepository.findByUsername(username);
	}
	public Employee findByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public int delete(Long id) {
		return employeeRepository.delete(id);
	}

	public Employee login(String username, String pass) {
		return employeeRepository.login(username, pass);
	}
	
	
	
}

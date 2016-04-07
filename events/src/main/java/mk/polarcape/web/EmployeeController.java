<<<<<<< HEAD
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

import mk.polarcape.model.Employee;
import mk.polarcape.service.EmployeeService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getemployees() {
		return employeeService.findAll();
	}

	@RequestMapping(value = "/employeeid/{id}", method = RequestMethod.GET)
	public Employee getemployeeById(@PathVariable Long id) {
		return employeeService.findById(id);
	}
	@RequestMapping(value = "/employeeusername/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getemployeeByUsername(@PathVariable String username) {
		return employeeService.findByUsername(username);
	}
	@RequestMapping(value = "/employeemail/{email}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getemployeeByEmail(@PathVariable String email) {
		return employeeService.findByEmail(email);
	}
	@RequestMapping(value="/employee", method = RequestMethod.POST)
	@ResponseBody
	public Employee createemployee(@RequestBody Employee employee){
		return employeeService.save(employee);
	}
	

	@RequestMapping(value="/employee/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee updateemployee(@PathVariable Long id, @RequestBody Employee employee){
		Employee currentemployee = employeeService.findById(id);
		
		currentemployee.setName(employee.getName());
		currentemployee.setSurname(employee.getSurname());
		currentemployee.setEmail(employee.getEmail());
		currentemployee.setUsername(employee.getUsername());
		currentemployee.setPassword(employee.getPassword());
		currentemployee.setActive(employee.isActive());
		
		return employeeService.save(currentemployee);
	}
	
	@RequestMapping(value="/employee/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteemployee(@PathVariable Long id){
		return employeeService.delete(id);
	}
	
	@RequestMapping(value = "/login/{username}/{pass}", method = RequestMethod.GET)
	@ResponseBody
	public Employee login(@PathVariable String username, @PathVariable String pass) {
		return employeeService.login(username, pass);
	}
	
}
=======
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

import mk.polarcape.model.Employee;
import mk.polarcape.service.EmployeeService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getemployees() {
		return employeeService.findAll();
	}

	@RequestMapping(value = "/employeeid/{id}", method = RequestMethod.GET)
	public Employee getemployeeById(@PathVariable Long id) {
		return employeeService.findById(id);
	}
	@RequestMapping(value = "/employeeusername/{username}", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Employee getemployeeByUsername(@PathVariable String username) {
		return employeeService.findByUsername(username);
	}
	@RequestMapping(value = "/employeemail/{email}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getemployeeByEmail(@PathVariable String email) {
		return employeeService.findByEmail(email);
	}
	
	@RequestMapping(value="/employee", method = RequestMethod.POST)
	@ResponseBody
	public Employee createemployee(@RequestBody Employee employee){
		return employeeService.save(employee);
	}
	

	@RequestMapping(value="/employee/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee updateemployee(@PathVariable Long id, @RequestBody Employee employee){
		Employee currentemployee = employeeService.findById(id);
		
		currentemployee.setName(employee.getName());
		currentemployee.setSurname(employee.getSurname());
		currentemployee.setEmail(employee.getEmail());
		currentemployee.setUsername(employee.getUsername());
		currentemployee.setPassword(employee.getPassword());
		currentemployee.setActive(employee.isActive());
		
		return employeeService.save(currentemployee);
	}
	
	@RequestMapping(value="/employee/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteemployee(@PathVariable Long id){
		return employeeService.delete(id);
	}
	
	@RequestMapping(value = "/login/{username}/{pass}", method = RequestMethod.GET)
	@ResponseBody
	public Employee login(@PathVariable String username, @PathVariable String pass) {
		return employeeService.login(username, pass);
	}
	
}
>>>>>>> refs/heads/milos

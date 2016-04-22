package mk.polarcape.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.polarcape.model.Employee;
import mk.polarcape.model.UserRole;
import mk.polarcape.security.UserAuthentication;
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
	@RequestMapping(value = "/employeemail/{email:.+}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getemployeeByEmail(@PathVariable String email) {
		return employeeService.findByEmail(email);
	}	
	@RequestMapping(value="/employee", method = RequestMethod.POST)
	@ResponseBody
	public Employee createemployee(@RequestBody Employee employee){
		//////////////////////////////////
		String pw_hash = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt(10)); 
		/////////////////
		employee.setPassword(pw_hash);
		return employeeService.save(employee);
	}
	

	@RequestMapping(value="/employee/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Employee updateemployee(@PathVariable Long id, @RequestBody Employee employee){
		Employee currentemployee = employeeService.findById(id);
		
	//	System.out.println("json password is :" + employee.getPassword());
		currentemployee.setName(employee.getName());
		currentemployee.setSurname(employee.getSurname());
		currentemployee.setEmail(employee.getEmail());
		///////////////////////////
		String pw_hash = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt(10)); 
		/////////////////////////
		currentemployee.setUsername(employee.getUsername());
		currentemployee.setPassword(pw_hash);
		currentemployee.setActive(employee.isActive());
	//	System.out.println("password after hashing :" + currentemployee.getPassword());
		
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
		return employeeService.login(username,  pass);
	}
	@RequestMapping(value = "/users/current", method = RequestMethod.GET)
	public Employee getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication) {
			return (Employee) authentication.getDetails();
		}
		return new Employee(authentication.getName()); //anonymous user support
	}

	@RequestMapping(value = "/users/{user}/grant/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> grantRole(@PathVariable Employee user, @PathVariable UserRole role) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		user.grantRole(role);
		employeeService.save(user);
		return new ResponseEntity<String>("role granted", HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{user}/revoke/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> revokeRole(@PathVariable Employee user, @PathVariable UserRole role) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		user.revokeRole(role);
		employeeService.save(user);
		return new ResponseEntity<String>("role revoked", HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<Employee> list() {
		return employeeService.findAll();
	}
}

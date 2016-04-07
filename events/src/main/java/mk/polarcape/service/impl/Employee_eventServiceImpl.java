package mk.polarcape.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.polarcape.model.Employee_event;
import mk.polarcape.repository.Employee_eventRepository;
import mk.polarcape.service.Employee_eventService;

@Service
public class Employee_eventServiceImpl implements Employee_eventService {
	@Autowired
	private Employee_eventRepository Employee_eventRepository;
	
	public Employee_event findById(Long id) {
		return Employee_eventRepository.findById(id);
	}

	public List<Employee_event> findAll() {
		return Employee_eventRepository.findAll();
	}

	public Employee_event save(Employee_event Employee_event) {
		return Employee_eventRepository.save(Employee_event);
	}

	public int delete(Long id) {
		return Employee_eventRepository.delete(id);
	}

}

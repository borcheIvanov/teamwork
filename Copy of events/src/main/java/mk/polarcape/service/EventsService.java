package mk.polarcape.service;

import java.util.List;

import mk.polarcape.model.Events;

public interface EventsService {
	Events findById(Long id);
	List<Events> findAll();
	Events save(Events user);
	int delete(Long id);
	
}

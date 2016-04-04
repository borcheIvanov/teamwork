package mk.polarcape.repository;

import java.util.List;

import mk.polarcape.model.Events;

public interface EventsRepository {
	Events findById(Long id);
	List<Events> findAll();
	Events save(Events user);
	int delete(Long id);
}

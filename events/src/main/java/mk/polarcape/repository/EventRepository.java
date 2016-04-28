package mk.polarcape.repository;

import java.util.List;

import mk.polarcape.model.Event;

public interface EventRepository {
	Event findById(Long id);
	List<Event> findAll();
	Event save(Event user);
	int delete(Long id);
	List<Event> findActive();
	List<Event> findClosed();
}

package mk.polarcape.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import mk.polarcape.model.Event;
import mk.polarcape.repository.EventRepository;
import mk.polarcape.service.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public Event findById(Long id) {
		return eventRepository.findById(id);
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public int delete(Long id) {
		return eventRepository.delete(id);
	}

	@Override
	public List<Event> findActive() {
		
		return eventRepository.findActive();
	}

	@Override
	public List<Event> findClosed() {
		return eventRepository.findClosed();
	}

	
}

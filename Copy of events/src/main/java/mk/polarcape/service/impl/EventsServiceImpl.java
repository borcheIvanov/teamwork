package mk.polarcape.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import mk.polarcape.model.Events;
import mk.polarcape.repository.EventsRepository;
import mk.polarcape.service.EventsService;

@Service
public class EventsServiceImpl implements EventsService {
	
	@Autowired
	private EventsRepository eventsRepository;
	
	public Events findById(Long id) {
		return eventsRepository.findById(id);
	}

	public List<Events> findAll() {
		return eventsRepository.findAll();
	}

	public Events save(Events events) {
		return eventsRepository.save(events);
	}

	public int delete(Long id) {
		return eventsRepository.delete(id);
	}

	
}

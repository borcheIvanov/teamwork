package mk.polarcape.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mk.polarcape.model.Event;
import mk.polarcape.repository.EventRepository;

@Repository
public class EventRepositoryImpl implements EventRepository{
	@PersistenceContext
	private EntityManager em;

	public Event findById(Long id) {
		Class<Event> type = Event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> cq = cb.createQuery(type);
		final Root<Event> root = cq.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cq.where(byId);

		TypedQuery<Event> query = em.createQuery(cq);

		return query.getSingleResult();

	}

	public List<Event> findAll() {
		Class<Event> type = Event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> cq = cb.createQuery(type);
		final Root<Event> root = cq.from(type);

		CriteriaQuery<Event> all = cq.select(root);

		TypedQuery<Event> allQuery = em.createQuery(all);

		return allQuery.getResultList();
	}

	@Transactional
	public Event save(Event event) {
		if (event.getId() != null && !em.contains(event)) {
			event = em.merge(event);
		} else {
			em.persist(event);
		}

		em.flush();
		return event;

	}

    @Transactional
	public int delete(Long id) {
		Class<Event> type = Event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Event> cd = cb.createCriteriaDelete(type);
		final Root<Event> root = cd.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cd.where(byId);

		int changes = em.createQuery(cd).executeUpdate();

		em.flush();

		return changes;

	}

	@Override
	public List<Event> findActive() {
		Class<Event> type = Event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> cq = cb.createQuery(type);
		final Root<Event> root = cq.from(type);

		Predicate p = cb.equal(root.get("archived"), false);

		cq.where(p);

		TypedQuery<Event> query = em.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public List<Event> findClosed() {
		Class<Event> type = Event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> cq = cb.createQuery(type);
		final Root<Event> root = cq.from(type);

		Predicate p = cb.equal(root.get("archived"), true);

		cq.where(p);

		TypedQuery<Event> query = em.createQuery(cq);

		return query.getResultList();
	}
	}

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

import mk.polarcape.model.Events;
import mk.polarcape.repository.EventsRepository;

@Repository
public class EventsRepositoryImpl implements EventsRepository{
	@PersistenceContext
	private EntityManager em;

	public Events findById(Long id) {
		Class<Events> type = Events.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Events> cq = cb.createQuery(type);
		final Root<Events> root = cq.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cq.where(byId);

		TypedQuery<Events> query = em.createQuery(cq);

		return query.getSingleResult();

	}

	public List<Events> findAll() {
		Class<Events> type = Events.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Events> cq = cb.createQuery(type);
		final Root<Events> root = cq.from(type);

		CriteriaQuery<Events> all = cq.select(root);

		TypedQuery<Events> allQuery = em.createQuery(all);

		return allQuery.getResultList();
	}

	@Transactional
	public Events save(Events event) {
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
		Class<Events> type = Events.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Events> cd = cb.createCriteriaDelete(type);
		final Root<Events> root = cd.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cd.where(byId);

		int changes = em.createQuery(cd).executeUpdate();

		em.flush();

		return changes;

	}

	

	
	}

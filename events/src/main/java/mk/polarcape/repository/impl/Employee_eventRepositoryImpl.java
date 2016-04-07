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

import mk.polarcape.model.Employee_event;
import mk.polarcape.repository.Employee_eventRepository;

@Repository
public class Employee_eventRepositoryImpl implements Employee_eventRepository {
	@PersistenceContext
	private EntityManager em;
	
	public Employee_event findById(Long id) {
		Class<Employee_event> type = Employee_event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee_event> cq = cb.createQuery(type);
		final Root<Employee_event> root = cq.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cq.where(byId);

		TypedQuery<Employee_event> query = em.createQuery(cq);

		return query.getSingleResult();

	}

	public List<Employee_event> findAll() {
		Class<Employee_event> type = Employee_event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee_event> cq = cb.createQuery(type);
		final Root<Employee_event> root = cq.from(type);

		CriteriaQuery<Employee_event> all = cq.select(root);

		TypedQuery<Employee_event> allQuery = em.createQuery(all);

		return allQuery.getResultList();

	}

	@Transactional
	public Employee_event save(Employee_event task) {
		if (task.getId() != null && !em.contains(task)) {
			task = em.merge(task);
		} else {
			em.persist(task);
		}

		em.flush();
		return task;
	}

	@Transactional
	public int delete(Long id) {
		Class<Employee_event> type = Employee_event.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Employee_event> cd = cb.createCriteriaDelete(type);
		final Root<Employee_event> root = cd.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cd.where(byId);

		int changes = em.createQuery(cd).executeUpdate();

		em.flush();

		return changes;

	}
}

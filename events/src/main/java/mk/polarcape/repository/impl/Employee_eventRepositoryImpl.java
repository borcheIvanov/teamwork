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
//////////////////
	public List<Employee_event> selectInvited(Long events_id) {
		Class<Employee_event> type = Employee_event.class;

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Employee_event> cq = cb.createQuery(type);
		final Root<Employee_event> root = cq.from(type);
	
		Predicate p2 = cb.equal(root.get("events_id"), events_id);
		
		cq.where(p2);

		final TypedQuery<Employee_event> query = em.createQuery(cq);
		return query.getResultList();
	}
	public List<Employee_event> selectHosting(Long hosting_id) {
		Class<Employee_event> type = Employee_event.class;

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Employee_event> cq = cb.createQuery(type);
		final Root<Employee_event> root = cq.from(type);
	
		Predicate p2 = cb.equal(root.get("hosting_id"), hosting_id);
		
		cq.where(p2);

		final TypedQuery<Employee_event> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Employee_event> selectWhere(Long invited_id) {
		Class<Employee_event> type = Employee_event.class;

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Employee_event> cq = cb.createQuery(type);
		final Root<Employee_event> root = cq.from(type);
	
		Predicate p2 = cb.equal(root.get("invited_id"), invited_id);
		
		cq.where(p2);

		final TypedQuery<Employee_event> query = em.createQuery(cq);
		return query.getResultList();
	}
	
	public List<Employee_event> InvitedNotPayed(Long events_id) {
		Class<Employee_event> type = Employee_event.class;

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Employee_event> cq = cb.createQuery(type);
		final Root<Employee_event> root = cq.from(type);
	
		Predicate p2 = cb.equal(root.get("events_id"), events_id);
		Predicate p3 = cb.equal(root.get("payed"), false);
		
		cq.where(p2,p3);

		final TypedQuery<Employee_event> query = em.createQuery(cq);
		return query.getResultList();
	}
}

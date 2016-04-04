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

import mk.polarcape.model.People;
import mk.polarcape.repository.PeopleRepository;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository {
	@PersistenceContext
	private EntityManager em;
	
	public People findById(Long id) {
		Class<People> type = People.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<People> cq = cb.createQuery(type);
		final Root<People> root = cq.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cq.where(byId);

		TypedQuery<People> query = em.createQuery(cq);

		return query.getSingleResult();

	}

	public List<People> findAll() {
		Class<People> type = People.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<People> cq = cb.createQuery(type);
		final Root<People> root = cq.from(type);

		CriteriaQuery<People> all = cq.select(root);

		TypedQuery<People> allQuery = em.createQuery(all);

		return allQuery.getResultList();

	}

	@Transactional
	public People save(People task) {
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
		Class<People> type = People.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<People> cd = cb.createCriteriaDelete(type);
		final Root<People> root = cd.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cd.where(byId);

		int changes = em.createQuery(cd).executeUpdate();

		em.flush();

		return changes;

	}

	public List<People> getInvitedPeoplesForEventsId(Long id) {
		Class<People> type = People.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<People> cq = cb.createQuery(type);
		final Root<People> root = cq.from(type);

		Predicate byInvited = cb.equal(root.get("invited"), id);

		cq.where(byInvited);

		TypedQuery<People> query = em.createQuery(cq);

		return query.getResultList();
	}
	public People login(String email, String pass) {
		Class<People> type = People.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<People> cq = cb.createQuery(type);
		final Root<People> root = cq.from(type);

		Predicate byEmail = cb.equal(root.get("email"), email);
		Predicate byPass = cb.equal(root.get("pass"), pass);
		
		cq.where(cb.and(byEmail, byPass));

		TypedQuery<People> query = em.createQuery(cq);

		return query.getSingleResult();
	}

	
}

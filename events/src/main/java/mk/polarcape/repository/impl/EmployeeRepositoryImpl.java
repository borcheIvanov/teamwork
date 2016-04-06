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

import mk.polarcape.model.Employee;
import mk.polarcape.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	@PersistenceContext
	private EntityManager em;
	
	public Employee findById(Long id) {
		Class<Employee> type = Employee.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(type);
		final Root<Employee> root = cq.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cq.where(byId);

		TypedQuery<Employee> query = em.createQuery(cq);

		return query.getSingleResult();

	}
	public Employee findByUsername(String username) {
		Class<Employee> type = Employee.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(type);
		final Root<Employee> root = cq.from(type);

		Predicate byusername = cb.equal(root.get("username"), username);

		cq.where(byusername);

		TypedQuery<Employee> query = em.createQuery(cq);

		return query.getSingleResult();

	}
	public Employee findByEmail(String email) {
		Class<Employee> type = Employee.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(type);
		final Root<Employee> root = cq.from(type);

		Predicate byEmail = cb.equal(root.get("email"), email);

		cq.where(byEmail);

		TypedQuery<Employee> query = em.createQuery(cq);

		return query.getSingleResult();

	}
	public List<Employee> findAll() {
		Class<Employee> type = Employee.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(type);
		final Root<Employee> root = cq.from(type);

		CriteriaQuery<Employee> all = cq.select(root);

		TypedQuery<Employee> allQuery = em.createQuery(all);

		return allQuery.getResultList();

	}

	@Transactional
	public Employee save(Employee task) {
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
		Class<Employee> type = Employee.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Employee> cd = cb.createCriteriaDelete(type);
		final Root<Employee> root = cd.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cd.where(byId);

		int changes = em.createQuery(cd).executeUpdate();

		em.flush();

		return changes;

	}
	public Employee login(String username, String pass) {
		Class<Employee> type = Employee.class;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(type);
		final Root<Employee> root = cq.from(type);

		Predicate byusername = cb.equal(root.get("username"), username);
		Predicate byPass = cb.equal(root.get("password"), pass);
		
		cq.where(cb.and(byusername, byPass));

		TypedQuery<Employee> query = em.createQuery(cq);

		return query.getSingleResult();
	}

	
}

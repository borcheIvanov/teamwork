package mk.polarcape.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mk.polarcape.model.Login;
import mk.polarcape.repository.LoginRepository;

@Repository
public class LoginRepositoryImpl implements LoginRepository {
	@PersistenceContext
	private EntityManager em;
	public Login login(String username, String pass) {
			Class<Login> type = Login.class;

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Login> cq = cb.createQuery(type);
			final Root<Login> root = cq.from(type);

			Predicate byusername = cb.equal(root.get("username"), username);
			Predicate byPass = cb.equal(root.get("pass"), pass);
			
			cq.where(cb.and(byusername, byPass));

			TypedQuery<Login> query = em.createQuery(cq);

			return query.getSingleResult();
		}
	

}

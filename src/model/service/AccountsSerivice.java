package model.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Accounts;

import com.example.admincdo.AdmincdoUI;

public class AccountsSerivice implements Serializable {

	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	EntityManager em = emf.createEntityManager();
	
	public List<Accounts> get () {
		Query query = em.createQuery("SELECT a FROM Accounts a");
		return query.getResultList();
	}
	
	public Accounts get(int id) {
		return em.find(Accounts.class, id);
	}
	
	public boolean isUnique (String username) {
		Query query = em.createQuery("SELECT acc FROM Accounts acc WHERE acc.username = :username");
		query.setParameter("username", username);
		
		return query.getResultList().isEmpty();
	}
	
	public void post (Accounts acc) {
		em.getTransaction().begin();
		
		em.persist(acc);
		
		em.getTransaction().commit();
	}
	
	
	public void update (Accounts acc) {
		em.getTransaction().begin();
		
		em.merge(acc);
		
		em.getTransaction().commit();
	}
}

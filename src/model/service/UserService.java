package model.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Accounts;
import model.entity.Information;
import model.entity.User_municipal;

import com.example.admincdo.AdmincdoUI;

public class UserService implements Serializable {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	EntityManager em = emf.createEntityManager();
	
	public void post_municipal (User_municipal user) {
		em.getTransaction().begin();
		
		em.persist(user);
		
		em.getTransaction().commit();
	}
	
	public List<User_municipal> get_municipal () {
		Query query = em.createQuery("SELECT um FROM User_municipal um");
		return query.getResultList();
	}
	
	public void delete_municipal (User_municipal user) {
		em.getTransaction().begin();
		
		em.remove(user.getInformation().getAccount());
		em.remove(user.getInformation());
		em.remove(user);
		
		em.getTransaction().commit();
	}
}

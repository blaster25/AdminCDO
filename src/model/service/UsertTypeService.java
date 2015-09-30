package model.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Usertype;

import com.example.admincdo.AdmincdoUI;

public class UsertTypeService implements Serializable {

	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	EntityManager em = emf.createEntityManager();
	
	public List<Usertype> get () {
		Query query = em.createQuery("SELECT s FROM Usertype s");
		
		return query.getResultList();
	}
	
	public Usertype get(int id) {
		return em.find(Usertype.class, id);
	}
}

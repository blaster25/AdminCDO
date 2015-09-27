package model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Barangay;

import com.example.admincdo.AdmincdoUI;

public class BarangayService {

	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	EntityManager em = emf.createEntityManager();
	
	public List<Barangay> get () {
		Query query = em.createQuery("SELECT b FROM Barangay b");
		return query.getResultList();
	}
	
	public Barangay get (int id) {
		return em.find(Barangay.class, id);
	}
	
	public void post (Barangay b) {
		em.getTransaction().begin();
		
		em.persist(b);
		
		em.getTransaction().commit();
	}
	
	public void put (Barangay b) {
		em.getTransaction().begin();
		
		Barangay edit = em.find(Barangay.class, b.getBarangayid());
		
		edit.setDate_registered(b.getDate_registered());
		edit.setDistrick(b.getDistrick());
		edit.setMunicipal(b.getMunicipal());
		edit.setName(b.getName());
		
		em.getTransaction().commit();
	}
	
	public void delete (int id) {
		em.getTransaction().begin();
		
		Barangay b = em.find(Barangay.class, id);
		
		em.remove(b);
		
		em.getTransaction().commit();
	}
	
	public void delete (Barangay b) {
		em.getTransaction().begin();
		
		em.remove(b);
		
		em.getTransaction().commit();
	}
}

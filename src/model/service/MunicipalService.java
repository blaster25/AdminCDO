package model.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Municipal;

import com.example.admincdo.AdmincdoUI;

public class MunicipalService implements Serializable {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	EntityManager em = emf.createEntityManager();
	
	public List<Municipal> get() {
		Query query = em.createQuery("SELECT m FROM Municipal m");
		List<Municipal> list = query.getResultList();
		
		return list;
	}
	
	public Municipal get(int id) {
		Municipal muni = em.find(Municipal.class, id);
		
		return muni;
	}
	public void post(Municipal m) {
		
		em.getTransaction().begin();
		
		em.persist(m);
		
		em.getTransaction().commit();
	}
	public void put(int id, Municipal m) {
		em.getTransaction().begin();
		
		Municipal mm = this.get(id);
		mm.setHasDistrick(m.getHasDistrick());
		mm.setName(m.getName());
		
		em.getTransaction().commit();
	}
	
	public void delete(int id) {
		em.getTransaction().begin();
		
		Municipal m = this.get(id);
		em.remove(m);
		
		em.getTransaction().commit();
	}
	
	/**
	 * 
	 * @param equal
	 * @return true if the result is empty
	 */
	public boolean count(String equal) {
		Query query = em.createQuery("SELECT m FROM Municipal m WHERE m.name = :findname");
		query.setParameter("findname", equal);
		
		return query.getResultList().isEmpty();
	}
}

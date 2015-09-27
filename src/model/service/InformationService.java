package model.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Information;

import com.example.admincdo.AdmincdoUI;

public class InformationService implements Serializable {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	EntityManager em = emf.createEntityManager();
	
	public List<Information> get (String type_name) {
		Query query = em.createQuery("SELECT info FROM Information info, Accounts acc WHERE info.account.accountid = acc.accountid AND acc.usertype.name = :type");
		query.setParameter("type", type_name);
		return query.getResultList();
	}
	
	public List<Information> get () {
		Query query = em.createQuery("SELECT info FROM Information info");
		return query.getResultList();
	}
	public Information get (int id) {
		Query query = em.createQuery("SELECT info FROM Information info WHERE info.infoid = :id");
		query.setParameter("id", id);
		return (Information) query.getSingleResult();
	}
	public void post (Information info) {
		em.getTransaction().begin();
		
		em.persist(info);
		em.getTransaction().commit();
	}
	public void put (int id, Information info) {
		em.getTransaction().begin();
		
		Information update = em.find(Information.class, id);
		update.setAccount(info.getAccount());
		update.setAddress(info.getAddress());
		update.setBirthdate(info.getBirthdate());
		update.setContact(info.getContact());
		update.setEmail(info.getEmail());
		update.setFname(info.getFname());
		update.setLname(info.getLname());
		update.setMname(info.getFname());
		
		em.getTransaction().commit();
	}
	public void delete (int id) {
		em.getTransaction().begin();
		
		Information toDelete = em.find(Information.class, id);
		em.remove(toDelete);
		
		em.getTransaction().commit();
	}

}

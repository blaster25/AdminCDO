package model.generate;

import java.sql.Date;
import java.util.HashMap;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Accounts;
import model.Assignment;
import model.Information;
import model.Usertype;

import com.example.admincdo.AdmincdoUI;

public class ExampleData {

	private static EntityManager em = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT).createEntityManager();
	private static String[] u_type = new String [] {"Administrator", "Municipal Staff", "Barangay Staff"};
	private static String[] ass_type = new String [] {"Municipal", "Barangay"};
	
	private static HashMap<Integer, Usertype> userTypeMap = new HashMap<Integer, Usertype>();
	private static HashMap<Integer, Assignment> assignmentTypeMap = new HashMap<Integer, Assignment>();
	
	private static String listAccounts[][] = new String [][] {
			{"losewin1", "losewin"},
			{"losewin2", "losewin"},
			{"losewin3", "losewin"},
			{"losewin4", "losewin"},
			{"losewin5", "losewin"},
			{"losewin6", "losewin"},
			{"losewin7", "losewin"},
			{"losewin8", "losewin"},
	};
	
	public ExampleData() {
		// TODO Auto-generated constructor stub
		em.getTransaction().begin();
			cleanTable();
			insertStaticData();
			
			for(String[] acc: listAccounts) {
				Accounts dbAccount = new Accounts(acc[0], acc[1], userTypeMap.get(generate(1, 3)));
				em.persist(dbAccount);
				Information dbInfo = new Information("Al Lestaire", "Gilig", "Acasio",
						"Male", new Date(3, 6, 1994), "dongskay@gmail.com",
						"Carmen", "09265634331", dbAccount, assignmentTypeMap.get(generate(1, assignmentTypeMap.size())));
				em.persist(dbInfo);
			}	
		em.getTransaction().commit();
		em.close();
	}
	
	public static void executeExamples () {
		em.getTransaction().begin();
			cleanTable();
			insertStaticData();
		em.getTransaction().commit();
		
	}
	
	private static void insertStaticData () {
		int iterate = 0;
		for(String s: u_type) {
			iterate++;
			Usertype type = new Usertype(s);
			em.persist(type);
			
			userTypeMap.put(iterate, type);
		}
		
		for(String s: ass_type) {
			Assignment ass = new Assignment(s);
			em.persist(ass);
			assignmentTypeMap.put(ass.getId(), ass);
		}
	}
	
	private static void cleanTable () {
		em.createQuery("DELETE FROM Usertype ut").executeUpdate();
		em.createQuery("DELETE FROM Accounts a").executeUpdate();
		em.createQuery("DELETE FROM Assignment ass").executeUpdate();
		em.createQuery("DELETE FROM Information info").executeUpdate();
	}
	
	private static int generate(int min,int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

}

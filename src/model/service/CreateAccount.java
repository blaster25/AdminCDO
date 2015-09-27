package model.service;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entity.Accounts;
import model.entity.Barangay;
import model.entity.Information;
import model.entity.Municipal;
import model.entity.User_barangay;
import model.entity.User_municipal;
import model.entity.User_voter;
import model.entity.Usertype;

import com.example.admincdo.AdmincdoUI;

public class CreateAccount {

	public CreateAccount() {
		// TODO Auto-generated constructor stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usertype municipal = new Usertype("Municipal");
		Usertype voter = new Usertype("Voter");
		Usertype barangay = new Usertype("Barangay");
		Usertype admin = new Usertype("Administrator");
		
		
		Accounts account1 = new Accounts("losewin1", "losewin", municipal);
		Accounts account2 = new Accounts("losewin2", "losewin", municipal);
		Accounts account3 = new Accounts("losewin3", "losewin", voter);
		Accounts account4 = new Accounts("losewin4", "losewin", barangay);
		Accounts account5 = new Accounts("losewin5", "losewin", barangay);
		Accounts account6 = new Accounts("losewin6", "losewin", admin);
		
		Municipal municipal1 = new Municipal("Cagayan De Oro City", true);
		Municipal municipal2 = new Municipal("Zamboanga City", false);
		
		Barangay barangay1 = new Barangay("Kauswagan", new Date(0), municipal1);
		Barangay barangay2 = new Barangay("Carmen", new Date(0), municipal1);
		Barangay barangay3 = new Barangay("Pagadian", new Date(0), municipal2);
		Barangay barangay4 = new Barangay("Bugo", new Date(0), municipal1);
		Barangay barangay5 = new Barangay("Parangka", new Date(0), municipal2);
		
		Information info1 = new Information(
				"April Marie", "Dalogdog", "Bandivas", "Female", new Date(0), "09265634331", "Bugo",
				"dongskay@gmail.com", account1);
		Information info2 = new Information(
				"Al Lestaire", "Gilig", "Acasio", "Male", new Date(0), "09265634331", "Upper Carmen",
				"dongskay@gmail.com", account2);
		Information info3 = new Information(
				"John Alfred", "Ruiz", "Catampo", "Male", new Date(0), "09265634331", "Butuan",
				"dongskay@gmail.com", account3);
		Information info4 = new Information(
				"Jerome", "Secret", "Pacana", "Male", new Date(0), "09265634331", "Talakag",
				"dongskay@gmail.com", account4);
		Information info5 = new Information(
				"Mary Ciolina", "Pepe", "Branzuela", "Female", new Date(0), "09265634331", "Kauswagan",
				"dongskay@gmail.com", account5);
		
		Information info6 = new Information(
				"Super Admin", "Super Admin", "Super Admin", "Male", new Date(0), "09265634331", "Kauswagan",
				"dongskay@gmail.com", account6);
		
		User_municipal usermunicipal1 = new User_municipal("Developer", "Programming", info1, municipal1);
		User_municipal usermunicipal2 = new User_municipal("Web Developer", "Designer", info2, municipal2);
		User_voter uservoter1 = new User_voter(false, "Teacher", info3, barangay1);
		User_barangay userbarangay1 = new User_barangay(info4, barangay2, "Encoder");
		User_barangay userbarangay2 = new User_barangay(info5, barangay3, "Encoder");
		
		em.persist(municipal);
		em.persist(voter);
		em.persist(barangay);
		em.persist(admin);
		
		em.persist(account1);
		em.persist(account2);
		em.persist(account3);
		em.persist(account4);
		em.persist(account5);
		em.persist(account6);
		
		em.persist(municipal1);
		em.persist(municipal2);
		
		em.persist(barangay1);
		em.persist(barangay2);
		em.persist(barangay3);
		em.persist(barangay4);
		em.persist(barangay5);
		
		em.persist(info1);
		em.persist(info2);
		em.persist(info3);
		em.persist(info4);
		em.persist(info5);
		em.persist(info6);
		
		em.persist(userbarangay2);
		em.persist(userbarangay1);
		em.persist(uservoter1);
		em.persist(usermunicipal2);
		em.persist(usermunicipal1);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}

}

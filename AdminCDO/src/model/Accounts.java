package model;

import java.io.Serializable;

import model.Usertype;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(
			query = "select a from Accounts a where a.username = :user and a.password = :pass",
			name = "find account"
		)
public class Accounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1037392515164653808L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	
	@ManyToOne
	private Usertype usertype;

	public Accounts() {
		// TODO Auto-generated constructor stub
	}
	
	public Accounts(String initUsername, String initPassword, Usertype initType) {
		// TODO Auto-generated constructor stub
		this.username = initUsername;
		this.password = initPassword;
		this.usertype = initType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Usertype getUserType () {
		return usertype;
	}
	
	public void setUserType (Usertype newUserType) {
		this.usertype = newUserType;
	}
	
	public String toString () {
		return this.username;
	}

}

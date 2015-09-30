package model.entity;

import java.io.Serializable;
import java.sql.Date;

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
public class Accounts implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountid;
	
	private String username;
	private String password;
	private Boolean isDisabled;
	private Date created_on;
	private Date updated_on;
	
	@ManyToOne
	private Usertype usertype;
	
	public Accounts () {
		super();
		this.isDisabled = false;
	}
	public Accounts (String username, String password, Usertype usertype) {
		super();
		this.username = username;
		this.password = password;
		this.isDisabled = false;
		this.created_on = new Date(0);
		this.usertype = usertype;
	}
	public int getId() {
		return accountid;
	}
	public void setId(int accountid) {
		this.accountid = accountid;
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
	public Boolean getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public Date getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}
	public Usertype getUsertype() {
		return usertype;
	}
	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "################## Account ###############\n"
				+ "ID : " + this.accountid + "\n"
				+ "Username : " + this.username + "\n"
				+ "Password : " + this.password + "\n"
				+ "Disabled : " + this.isDisabled + "\n"
				+ "Usertype : " + this.usertype.getName() + "\n"
				+ "######################################";
	}
	
}

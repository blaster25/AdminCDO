package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(
		query = "select info from Information info where info.id = :infoid",
		name = "find info"
	)
public class Information implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fname;
	private String mname;
	private String lname;
	
	private String gender;
	private Date birthdate;
	private String contact;
	private String email;
	private String address;
	
	@OneToOne
	private Accounts account;
	
	@ManyToOne
	private Assignment assignment;
	
	public Information() {
		// TODO Auto-generated constructor stub
	}
	
	public Information (String initFname, String initMname,
			String initLname, String initGender, Date initDate,
			String initEmail, String initAddress, String initContact,
			Accounts initAccounts, Assignment initAssignment) {
		this.fname = initFname;
		this.mname = initMname;
		this.lname = initLname;
		this.gender = initGender;
		this.birthdate = initDate;
		this.contact = initContact;
		this.email = initEmail;
		this.address = initAddress;
		this.account = initAccounts;
		this.assignment = initAssignment;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}

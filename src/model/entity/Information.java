package model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
@NamedQuery(
		query = "select info from Information info where info.infoid = :infoid",
		name = "find info"
	)
public class Information implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int infoid;
	
	@NotNull(message = "First name must not be empty")
	@Size(min= 5, max=50)
	private String fname;
	
	@Size(min= 5, max=50, message = "Middle name must not be less than 5 or not more than 50 character")
	private String mname;
	
	@NotNull
	@Size(min= 5, max=50)
	private String lname;
	
	@NotNull
	private String gender;
	private Date birthdate;
	private String contact;
	private String address;
	
	private String email;
	
	@OneToOne
	private Accounts account;
	
	public Information() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Information(String fname, String mname, String lname, String gender,
			Date birthdate, String contact, String address, String email, Accounts account) {
		super();
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.contact = contact;
		this.address = address;
		this.email = email;
		this.account = account;
	}

	public int getInfoid() {
		return infoid;
	}

	public void setInfoid(int infoid) {
		this.infoid = infoid;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "############ Information ##############\n"
				+ "Full name : " + this.fname + " " + this.mname + " " + this.lname + "\n"
				+ "Gender : " + this.gender + "\n"
				+ "Birthdate : " + this.birthdate + "\n"
				+ "Contact : " + this.contact + "\n"
				+ "Address : " + this.address  + "\n"
				+ "E-mail Address : " + this.email + "\n"
				+ "Account ID : " + this.account.getId();
	}

}

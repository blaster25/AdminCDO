package model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Barangay implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int barangayid;
	
	private String name;
	private int districk;
	private Date date_registered;
	
	@ManyToOne
	private Municipal municipal;
	
	public Barangay() {
		// TODO Auto-generated constructor stub
		super();
		this.name = "";
		this.date_registered = new Date(0);
	}

	public Barangay(String name, Date date_registered,
			Municipal municipal) {
		super();
		this.name = name;
		this.date_registered = date_registered;
		this.municipal = municipal;
	}

	public int getBarangayid() {
		return barangayid;
	}

	public void setBarangayid(int barangayid) {
		this.barangayid = barangayid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate_registered() {
		return date_registered;
	}

	public void setDate_registered(Date date_registered) {
		this.date_registered = date_registered;
	}

	public Municipal getMunicipal() {
		return municipal;
	}

	public void setMunicipal(Municipal municipal) {
		this.municipal = municipal;
	}

	public int getDistrick() {
		return districk;
	}

	public void setDistrick(int districk) {
		this.districk = districk;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return "###################### Barangay ################\n"
				+ "ID : " + this.barangayid + "\n"
				+ "Name : " + this.name + "\n"
				+ "Districk : " + this.districk + "\n"
				+ "Date Registered : " + this.date_registered + "\n"
				+ "Municipal ID : " + this.municipal.getMunicipalid() + "\n";
	}
	
	

}

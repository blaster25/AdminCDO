package model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table
public class User_municipal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usermunicipalid;
	
	private String position;
	private String expertise;
	
	@OneToOne
	private Information information;
	
	@OneToOne
	private Municipal municipal;
	
	public User_municipal() {
		// TODO Auto-generated constructor stub
		super();
	}

	public User_municipal(String position, String expertise,
			Information information, Municipal municipal) {
		super();
		this.position = position;
		this.expertise = expertise;
		this.information = information;
		this.municipal = municipal;
	}

	public int getUsermunicipalid() {
		return usermunicipalid;
	}

	public void setUsermunicipalid(int usermunicipalid) {
		this.usermunicipalid = usermunicipalid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public Municipal getMunicipal() {
		return municipal;
	}

	public void setMunicipal(Municipal municipal) {
		this.municipal = municipal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "########### User Municipal ###########\n"
				+ "ID : " + this.usermunicipalid + "\n"
				+ "Position : " + this.position + "\n"
				+ "Expertise : " + this.expertise + "\n"
				+ "Information ID : " + this.information.getInfoid() + "\n"
				+ "Municipal ID : " + this.municipal.getMunicipalid() + "\n";
	}
	
	
}

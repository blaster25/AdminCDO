package model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table
public class User_barangay implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userbarangayid;
	
	@OneToOne
	private Information information;
	
	@OneToOne
	private Barangay barangay;
	
	private String position;
	
	public User_barangay() {
		// TODO Auto-generated constructor stub
		super();
	}

	public User_barangay(Information information, Barangay barangay,
			String position) {
		super();
		this.information = information;
		this.barangay = barangay;
		this.position = position;
	}

	public int getUserbarangayid() {
		return userbarangayid;
	}

	public void setUserbarangayid(int userbarangayid) {
		this.userbarangayid = userbarangayid;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public Barangay getBarangay() {
		return barangay;
	}

	public void setBarangay(Barangay barangay) {
		this.barangay = barangay;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "############### Barangay USER ###################\n"
				+ "ID : " + this.userbarangayid + "\n"
				+ "Position : " + this.position + "\n"
				+ "Information ID : " + this.information.getInfoid() + "\n"
				+ "Barangay ID : " + this.barangay.getBarangayid() + "\n";
	}


}

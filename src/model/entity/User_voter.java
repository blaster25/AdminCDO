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
public class User_voter implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uservoterid;
	private Boolean isVoted;
	private String work;
	
	@OneToOne
	private Information information;
	
	@OneToOne
	private Barangay barangay;
	
	public User_voter () {
		super();
	}

	public User_voter(Boolean isVoted, String work, Information information,
			Barangay barangay) {
		super();
		this.isVoted = isVoted;
		this.work = work;
		this.information = information;
		this.barangay = barangay;
	}

	public int getVoterid() {
		return uservoterid;
	}

	public void setVoterid(int voterid) {
		this.uservoterid = voterid;
	}

	public Boolean getIsVoted() {
		return isVoted;
	}

	public void setIsVoted(Boolean isVoted) {
		this.isVoted = isVoted;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "############# Voter #################\n"
				+ "ID : " + this.uservoterid + "\n"
				+ "Is voter : " + this.isVoted + "\n"
				+ "Work : " + this.work + "\n"
				+ "Information ID : " + this.information.getInfoid() + "\n"
				+ "Barangay ID : " + this.barangay.getBarangayid() + "\n";
	}

}

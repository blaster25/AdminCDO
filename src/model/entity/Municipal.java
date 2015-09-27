package model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Municipal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int municipalid;
	
	@NotNull
	private String name;
	private Boolean hasDistrick;
	
	public Municipal() {
		// TODO Auto-generated constructor stub
		super();
		this.name = "";
		this.hasDistrick = false;
	}


	public Municipal(String name, Boolean hasDistrick) {
		super();
		this.name = name;
		this.hasDistrick = hasDistrick;
	}


	public int getMunicipalid() {
		return municipalid;
	}


	public void setMunicipalid(int municipalid) {
		this.municipalid = municipalid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Boolean getHasDistrick() {
		return hasDistrick;
	}


	public void setHasDistrick(Boolean hasDistrick) {
		this.hasDistrick = hasDistrick;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "################### Municipal #############\n"
				+ "ID : " + this.municipalid + "\n"
				+ "Name : " + this.name + "\n"
				+ "Has District : " + this.hasDistrick + "\n";
	}

}

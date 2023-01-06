package main.java.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drugs")
public class Drugs {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String drugName;
	
	@Column(name="form")
	private String form;
	
	@Column(name="dosage")
	private String dosage;
	
	@Column(name="ingredient")
	private String activeIngred;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getActiveIngred() {
		return activeIngred;
	}

	public void setActiveIngred(String activeIngred) {
		this.activeIngred = activeIngred;
	}

}
	

package com.arbormetrix.excercise.to;

import javax.xml.bind.annotation.XmlElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class PatientTO {
	private int patientId;

	@JsonProperty(access = Access.WRITE_ONLY)
	private transient int id;
	private String sex;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String gender;

	@JsonProperty(access = Access.WRITE_ONLY)
	private transient String dob;
	private String state;

	private String name;

	private int age;

	public int getId() {
		return patientId;
	}

	@XmlElement
	public void setId(int id) {
		this.patientId = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	@XmlElement
	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getState() {
		return state;
	}

	@XmlElement
	public void setState(String state) {
		this.state = state;
	}

	public String getGender() {
		return sex;
	}

	@XmlElement
	public void setGender(String gender) {
		this.sex = gender;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + patientId + ", name = " + name + ", dob = " + dob + ", state = " + state
				+ ", gender = " + sex + "]";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}

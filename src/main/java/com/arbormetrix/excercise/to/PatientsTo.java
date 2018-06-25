package com.arbormetrix.excercise.to;

public class PatientsTo {
	private Patients patients;

	public Patients getPatients() {
		return patients;
	}

	public void setPatients(Patients patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "ClassPojo [patients = " + patients + "]";
	}

}

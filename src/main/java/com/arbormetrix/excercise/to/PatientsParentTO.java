package com.arbormetrix.excercise.to;

public class PatientsParentTO {
	private PatientsTO patients;

	public PatientsTO getPatients() {
		return patients;
	}

	public void setPatients(PatientsTO patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "ClassPojo [patients = " + patients + "]";
	}

}

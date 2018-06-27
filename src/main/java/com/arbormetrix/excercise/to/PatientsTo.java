package com.arbormetrix.excercise.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="patients")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientsTO
{
	@XmlElement(name="patient")
    private PatientTO[] patient;

    public PatientTO[] getPatient ()
    {
        return patient;
    }

    public void setPatient (PatientTO[] patient)
    {
        this.patient = patient;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [patient = "+patient+"]";
    }
}
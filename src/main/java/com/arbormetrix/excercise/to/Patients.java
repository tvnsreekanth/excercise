package com.arbormetrix.excercise.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="patients")
@XmlAccessorType(XmlAccessType.FIELD)
public class Patients
{
	@XmlElement(name="patient")
    private Patient[] patient;

    public Patient[] getPatient ()
    {
        return patient;
    }

    public void setPatient (Patient[] patient)
    {
        this.patient = patient;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [patient = "+patient+"]";
    }
}
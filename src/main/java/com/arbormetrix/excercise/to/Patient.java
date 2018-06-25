package com.arbormetrix.excercise.to;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Patient
{
    private String patientId;

    private String name;

    private String dob;

    private String state;

    private String sex;

    public String getId ()
    {
        return patientId;
    }
    @XmlElement
    public void setId (String id)
    {
        this.patientId = id;
    }

    public String getName ()
    {
        return name;
    }
	@XmlElement
    public void setName (String name)
    {
        this.name = name;
    }

    public String getDob ()
    {
        return dob;
    }
	@XmlElement
    public void setDob (String dob)
    {
        this.dob = dob;
    }

    public String getState ()
    {
        return state;
    }
	@XmlElement
    public void setState (String state)
    {
        this.state = state;
    }

    public String getGender ()
    {
        return sex;
    }
	@XmlElement
    public void setGender (String gender)
    {
        this.sex = gender;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+patientId+", name = "+name+", dob = "+dob+", state = "+state+", gender = "+sex+"]";
    }
}
		

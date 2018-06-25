package com.arbormetrix.excercise;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.JSONArray;

import com.arbormetrix.excercise.to.Patient;
import com.arbormetrix.excercise.to.Patients;
import com.arbormetrix.excercise.utility.ExcerciseUtility;
import com.arbormetrix.excercise.utility.ReadXMLUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Example {

	public static void main(String[] args) throws Exception {
		ReadXMLUtility readXMLUtility = new ReadXMLUtility();
		String xmlString = readXMLUtility.getFile("input.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Patients.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);

		Patients patients = (Patients) unmarshaller.unmarshal(reader);
		Patient[] patientArray = patients.getPatient();
		List<Patient> patientList = new ArrayList<Patient>();
		for(Patient patient : patientArray) {
			System.out.println("patient.getDob()-->"+patient.getDob());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar dob = Calendar.getInstance();
	        dob.setTime(sdf.parse(patient.getDob()));
	        System.out.println("Age is:" + getAge(dob));
	        int age = getAge(dob);
	        patient.setDob(Integer.toString(age));
	        patientList.add(patient);
	        patient.setState(new ExcerciseUtility().STATE_MAP.get(patient.getState()));
	        patient.setGender((new ExcerciseUtility().GENDER_MAP.get(patient.getGender())));

	        patientList.add(patient);

			
		}
		patientList.toArray(patientArray);
		String jsonString = convertObjectToJson(patients);
		
		System.out.println(jsonString);
		//JSONArray array = new JSONArray(jsonString);
		//System.out.println("array = "+array);

		//System.out.println(new StringBuffer().replace(0, 10, convertObjectToJson(patients)));

	}
	
	public static int getAge(Calendar dob) throws Exception {
        Calendar today = Calendar.getInstance();

        int curYear = today.get(Calendar.YEAR);
        int dobYear = dob.get(Calendar.YEAR);

        int age = curYear - dobYear;

        // if dob is month or day is behind today's month or day
        // reduce age by 1
        int curMonth = today.get(Calendar.MONTH);
        int dobMonth = dob.get(Calendar.MONTH);
        if (dobMonth > curMonth) { // this year can't be counted!
            age--;
        } else if (dobMonth == curMonth) { // same month? check for day
            int curDay = today.get(Calendar.DAY_OF_MONTH);
            int dobDay = dob.get(Calendar.DAY_OF_MONTH);
            if (dobDay > curDay) { // this year can't be counted!
                age--;
            }
        }

        return age;
    }

	public static String convertObjectToJson(Patients patients) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.disableHtmlEscaping();
		Gson gson = gsonBuilder.create();
		return gson.toJson(patients, Patients.class);
	}

}

package com.arbormetrix.excercise;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.arbormetrix.excercise.to.PatientTO;
import com.arbormetrix.excercise.to.PatientsTO;
import com.arbormetrix.excercise.utility.ExcerciseConstants;
import com.arbormetrix.excercise.utility.ExcerciseUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrintJSONFromGson {

	public static void main(String[] args) throws Exception {
		ExcerciseUtility excerciseUtility = new ExcerciseUtility();
		String xmlString = excerciseUtility.getFile("input.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(PatientsTO.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);

		PatientsTO patients = (PatientsTO) unmarshaller.unmarshal(reader);
		PatientTO[] patientArray = patients.getPatient();
		List<PatientTO> patientList = new ArrayList<PatientTO>();
		for(PatientTO patient : patientArray) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar dob = Calendar.getInstance();
	        dob.setTime(sdf.parse(patient.getDob()));
	        int age = excerciseUtility.getAge(dob);
	        patient.setAge(age);
	        patientList.add(patient);
	        patient.setState(new ExcerciseConstants().STATE_MAP.get(patient.getState()));
	        patient.setGender((new ExcerciseConstants().GENDER_MAP.get(patient.getGender())));

	        patientList.add(patient);

			
		}
		patientList.toArray(patientArray);
		String jsonString = convertObjectToJson(patients);
		
		System.out.println("jsonInString= "+jsonString);
		
	}
	
	

	public static String convertObjectToJson(PatientsTO patients) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.disableHtmlEscaping();
		Gson gson = gsonBuilder.create();
		return gson.toJson(patients, PatientsTO.class);
	}

}

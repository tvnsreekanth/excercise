package com.arbormetrix.excercise;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.arbormetrix.excercise.to.Patient;
import com.arbormetrix.excercise.to.Patients;
import com.arbormetrix.excercise.to.PatientsTo;
import com.arbormetrix.excercise.utility.AgeCalculator;
import com.arbormetrix.excercise.utility.ExcerciseUtility;
import com.arbormetrix.excercise.utility.ReadXMLUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONExample

{
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) throws Exception {
		ReadXMLUtility readXMLUtility = new ReadXMLUtility();
		String xmlString = readXMLUtility.getFile("input.xml");
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
			AgeCalculator ageCalculator = new AgeCalculator();
			
			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			//System.out.println(jsonPrettyPrintString);
		    JSONArray jsonArray = xmlJSONObj.names();
		    List<String> keyList = new ArrayList<String>();
			ObjectMapper mapper = new ObjectMapper();
			PatientsTo patientsTo = mapper.readValue(jsonPrettyPrintString, PatientsTo.class);
			//System.out.println("patients = "+patientsTo);
			Patients patients = patientsTo.getPatients();
			Patient[] patientArray = patients.getPatient();
			List<Patient> patientList = new ArrayList<Patient>();
			for(Patient patient: patientArray) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		        Calendar dob = Calendar.getInstance();
		        dob.setTime(sdf.parse(patient.getDob()));
				patient.setDob(Integer.toString(ageCalculator.getAge(dob)));
				System.out.println("dob = "+patient.getDob());
		        patient.setState(new ExcerciseUtility().STATE_MAP.get(patient.getState()));
		        patient.setGender((new ExcerciseUtility().GENDER_MAP.get(patient.getGender())));
		        patientList.add(patient);

				
			}
			patientList.toArray(patientArray);
			patients.setPatient(patientArray);

			String jsonInString = mapper.writeValueAsString(patients);
			//System.out.println(jsonInString);
			
			//Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patients);
			System.out.println(jsonInString);


//		    for (int i = 0; i < jsonArray.length(); i++) {
//		    	for (String key : keyList) {
//		    		JSONObject json = ja.getJSONObject(i);
//		    		if (json.getString(key).equals("null")) {
//		    			json.remove(key);
//		    		}
//		    	}
//		    }


		} catch (JSONException je) {
			System.out.println(je.toString());
		}

	}



}

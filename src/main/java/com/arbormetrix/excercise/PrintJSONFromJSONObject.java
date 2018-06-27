package com.arbormetrix.excercise;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;

import com.arbormetrix.excercise.to.PatientTO;
import com.arbormetrix.excercise.to.PatientsParentTO;
import com.arbormetrix.excercise.to.PatientsTO;
import com.arbormetrix.excercise.utility.ExcerciseConstants;
import com.arbormetrix.excercise.utility.ExcerciseUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrintJSONFromJSONObject

{
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) throws Exception {
		ExcerciseUtility excerciseUtility = new ExcerciseUtility();
		String xmlString = excerciseUtility.getFile("input.xml");
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);

			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			ObjectMapper mapper = new ObjectMapper();
			PatientsParentTO patientsTo = mapper.readValue(jsonPrettyPrintString, PatientsParentTO.class);
			PatientsTO patients = patientsTo.getPatients();
			PatientTO[] patientArray = patients.getPatient();
			List<PatientTO> patientList = new ArrayList<PatientTO>();
			for (PatientTO patient : patientArray) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Calendar dob = Calendar.getInstance();
				dob.setTime(sdf.parse(patient.getDob()));
				patient.setAge((excerciseUtility.getAge(dob)));
				patient.setState(new ExcerciseConstants().STATE_MAP.get(patient.getState()));
				patient.setSex((new ExcerciseConstants().GENDER_MAP.get(patient.getGender())));

				patientList.add(patient);

			}
			patientList.toArray(patientArray);
			patients.setPatient(patientArray);

			String jsonInString = mapper.writeValueAsString(patientArray);

			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patientArray);
			System.out.println("jsonInString ="+jsonInString);

		} catch (Exception je) {
			System.out.println(je.toString());
		}

	}

}

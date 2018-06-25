package com.arbormetrix.excercise;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.arbormetrix.excercise.to.Patient;
import com.arbormetrix.excercise.to.Patients;
import com.arbormetrix.excercise.utility.ReadXMLUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JAXBExample {
	public static void main(String[] args) throws Exception {


		ReadXMLUtility readXMLUtility = new ReadXMLUtility();
		String xmlString = readXMLUtility.getFile("input.xml");
		System.out.println("xmlString= "+xmlString);
		JAXBContext jaxbContext = JAXBContext.newInstance(Patients.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);

		Patients patients = (Patients) unmarshaller.unmarshal(reader);
		Patient[] patientArray = patients.getPatient();
		for(Patient patient : patientArray) {
			
			
		}
          System.out.println(asJson(patients));       

	}
	private static String asJson(Object obj) throws Exception {
        StringWriter w = new StringWriter();
        new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(w, obj);
        String result = w.toString();
        return result;
    }
}
package com.arbormetrix.excercise;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.arbormetrix.excercise.utility.ExcerciseUtility;


public class PrintJSONString {
	
private static int PRETTY_PRINT_INDENT_FACTOR =4;

	public static void main(String[] args) {
		ExcerciseUtility excerciseUtility = new ExcerciseUtility();
		String xmlString = excerciseUtility.getFile("input.xml");
		JSONObject jsonObject = XML.toJSONObject(xmlString);
		JSONArray jsonArray = retrieveJSONArray(jsonObject);
		String jsonString = jsonArray.toString(PRETTY_PRINT_INDENT_FACTOR);
		System.out.println("jsonInString = "+jsonString);

	}
	public static JSONArray retrieveJSONArray(JSONObject jsonObject) {
		JSONArray array = new JSONArray();
		for(String key:JSONObject.getNames(jsonObject)) {
			JSONObject subNodeObject = jsonObject.optJSONObject(key);
			Iterator i = subNodeObject.keys();
			while(i.hasNext()) {
				String subKey = (String) i.next();
				array.put(subNodeObject.get(subKey));
							}
		}
		return array;
		
	}
}
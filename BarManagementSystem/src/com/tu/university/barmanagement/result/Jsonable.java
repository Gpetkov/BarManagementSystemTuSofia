package com.tu.university.barmanagement.result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * This interface gives us ability to represent an object as Json
 * 
 * @author GPetkov
 * 
 */
public interface Jsonable {
	/**
	 * This is gson object for all Jsonables
	 * 
	 */
	Gson gson = new GsonBuilder().serializeNulls().create();

	/**
	 * This method represents converting from object to json
	 * 
	 */
	String toJson();
}

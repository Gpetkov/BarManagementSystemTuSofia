package com.tu.university.barmanagement.result;

import com.google.gson.Gson;


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
	Gson gson = new Gson();

	/**
	 * This method represents converting from object to json
	 * 
	 */
	String toJson();
}

package com.tu.sofia.bg.resource.result;

import com.google.gson.Gson;

/**
 * This interface gives us ability to represent an object as Json
 * 
 * @author GPetkov
 * 
 */
public interface Jsonable {
	Gson gson = new Gson();
	String toJson();
}

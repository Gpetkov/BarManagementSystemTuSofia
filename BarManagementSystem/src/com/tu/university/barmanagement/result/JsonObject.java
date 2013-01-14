package com.tu.university.barmanagement.result;

import org.hibernate.proxy.HibernateProxy;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

import com.google.gson.*;
/**
 * This class represents an object that can be serialized to/from Json
 * 
 * @author GPetkov
 * 
 */
public class JsonObject implements Jsonable {
	/**
	 * Serialize the current object to Json
	 */
	@Override
	public String toJson() {
		return JsonObject.gson.toJson(this);
	}

	/**
	 * Deserialize json text to java object
	 * 
	 * @param json
	 *            the json source
	 * @param classType
	 *            the type of the java object
	 * @return the deserialized java object
	 */
	public static <T> T parseJson(String json, Class<T> classType) {
		return JsonObject.gson.fromJson(json, classType);
	}
}

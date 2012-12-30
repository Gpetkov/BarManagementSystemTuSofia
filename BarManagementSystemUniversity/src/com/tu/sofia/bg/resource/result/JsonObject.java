package com.tu.sofia.bg.resource.result;

/**
 * This class represents an object that can be serialized to/from Json
 * 
 * @author GPetkov
 * 
 */
public class JsonObject implements Jsonable
{
	/**
	 * This method serialize an object to Json
	 * 
	 */
	@Override
	public String toJson() 
	{
		return JsonObject.gson.toJson(this);
	}
	
	/**
	 * This method deserialize Json to Java Object
	 * 
	 */
	public static <T> T parseJson(String json, Class<T> classType)
	{
		return JsonObject.gson.fromJson(json, classType);
	}
}

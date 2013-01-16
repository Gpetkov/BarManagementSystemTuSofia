package com.tu.university.barmanagement.result;

import java.lang.reflect.Type;

import org.hibernate.proxy.HibernateProxy;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class HibernateProxySerializer implements JsonSerializer<HibernateProxy> {

	@Override
	public JsonElement serialize(HibernateProxy proxyObj, Type arg1,
			JsonSerializationContext arg2) {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			// below ensures deep deproxied serialization
			gsonBuilder.registerTypeHierarchyAdapter(HibernateProxy.class,
					new HibernateProxySerializer());
			Object deProxied = proxyObj.getHibernateLazyInitializer()
					.getImplementation();
			return gsonBuilder.create().toJsonTree(deProxied);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
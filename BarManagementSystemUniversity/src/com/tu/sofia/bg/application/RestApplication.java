package com.tu.sofia.bg.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.tu.sofia.bg.resource.ItemResource;
import com.tu.sofia.bg.resource.UserResource;
import com.tu.sofia.bg.resource.TestResource;
import com.tu.sofia.bg.resource.OrderResource;

public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		//Set of classes(resources)
		Set<Class<?>> classes = new HashSet<Class<?>>();
		//Adding the resources 
		classes.add(ItemResource.class);
		classes.add(OrderResource.class);
		classes.add(UserResource.class);
		classes.add(TestResource.class);
		return classes;
	}
}

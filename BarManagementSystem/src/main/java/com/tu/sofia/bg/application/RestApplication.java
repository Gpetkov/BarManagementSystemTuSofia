package com.tu.sofia.bg.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.tu.sofia.bg.resource.BarmanResource;
import com.tu.sofia.bg.resource.ManagerResource;
import com.tu.sofia.bg.resource.TestResource;
import com.tu.sofia.bg.resource.WaiterResource;

public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		//Set of classes(resources)
		Set<Class<?>> classes = new HashSet<Class<?>>();
		//Adding the resources 
		classes.add(BarmanResource.class);
		classes.add(WaiterResource.class);
		classes.add(ManagerResource.class);
		classes.add(TestResource.class);
		return classes;
	}
}

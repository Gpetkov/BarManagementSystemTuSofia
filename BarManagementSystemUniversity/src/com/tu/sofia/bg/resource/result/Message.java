package com.tu.sofia.bg.resource.result;


/**
 * This class represents a status message from the server
 * 
 * @author GPetkov
 * 
 */
public class Message {
	public static final int INFO = 1;
	public static final int WARNING = 2;
	public static final int ERROR = 3;
	
	private int status;
	private String data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}

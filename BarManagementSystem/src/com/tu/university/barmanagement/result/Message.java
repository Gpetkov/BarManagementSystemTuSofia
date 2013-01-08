package com.tu.university.barmanagement.result;

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

	/**
	 * @return Status of the message
	 * 
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status
	 *            Status of the message
	 * 
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return Message description
	 * 
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data
	 *            Message description
	 * 
	 */
	public void setData(String data) {
		this.data = data;
	}

}

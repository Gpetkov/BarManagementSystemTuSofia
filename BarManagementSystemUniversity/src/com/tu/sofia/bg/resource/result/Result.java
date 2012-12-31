package com.tu.sofia.bg.resource.result;

import java.util.List;

/**
 * This class represent the result to the client. Holds the status of the
 * executed operation, messages and the result object
 * 
 * @author GPetkov
 * 
 * @param <T>
 *            the type of the result object
 */
public class Result<T> extends JsonObject {
	public static final int SUCCESS = 0;
	public static final int FAIL = 1;

	private int status;
	private List<Message> messages;
	private T data;

	/**
	 * @return Current result status
	 * 
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status
	 *            Current result status
	 * 
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return List of message for the result
	 * 
	 */
	public List<Message> getMessages() {
		return messages;
	}
	/**
	 * @param messages
	 *            List of descriptions for the result
	 * 
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	/**
	 * @return Current result data
	 * 
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data
	 *            Current result data
	 * 
	 */
	public void setData(T data) {
		this.data = data;
	}

}

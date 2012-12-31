package com.tu.sofia.bg.model;

/**
 * This class represent's the status for current customer order
 * 
 * @author GPetkov
 * 
 */
public class OrderStatus {
	private Integer id;
	private String value;

	/**
	 * @return Status value
	 * 
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value
	 *            Status value
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return Status id
	 * 
	 */
	public Integer getId() {
		return id;
	}
}

package com.tu.sofia.bg.model;

/**
 * This class represent's current table for customer order
 * 
 * @author GPetkov
 * 
 */
public class Table {
	private Integer id;
	private Integer number;

	/**
	 * @return Table's number
	 * 
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * @param number
	 *            Table's number
	 * 
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * @return Table's id
	 * 
	 */
	public Integer getId() {
		return id;
	}
}

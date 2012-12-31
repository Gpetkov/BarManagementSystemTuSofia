package com.tu.sofia.bg.model;

/**
 * This class represent's current item from the order
 * 
 * @author GPetkov
 * 
 */
public class Item {
	private Integer id;
	private String name;
	private Double price;
	private String type;
	private Integer quantity;

	/**
	 * @return Name of the item
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 *            name for the item
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Price for the item
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price
	 *            Price for the item
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return Type of the item
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type
	 *            Type for the item
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return id Item id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return Item quantity
	 * 
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity
	 *            Item quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

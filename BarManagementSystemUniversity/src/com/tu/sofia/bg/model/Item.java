package com.tu.sofia.bg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * This class represent's current item from the order
 * 
 * @author GPetkov
 * 
 */
@Entity
@Table(name = "ITEM")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "ITM_ID")),
		@AttributeOverride(name = "dateCreated", column = @Column(name = "ITM_DATE_CREATED")),
		@AttributeOverride(name = "dateUpdated", column = @Column(name = "ITM_DATE_UPDATED"))})
@AssociationOverrides({
		@AssociationOverride(name = "createdByUser", joinColumns = @JoinColumn(name = "ITM_CREATED_BY_USER_ID")),
		@AssociationOverride(name = "updatedByUser", joinColumns = @JoinColumn(name = "ITM_UPDATED_BY_USER_ID"))})
public class Item extends ModelBase implements Serializable {
	/**
	 * Default version Id
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ITM_NAME", nullable = false, length = 50)
	private String name;
	@Column(name = "ITM_PRICE", nullable = false)
	private Double price;
	@Column(name = "ITM_TYPE", nullable = false, length = 50)
	private String type;
	@Column(name = "ITM_QUANTITY", nullable = false)
	private Integer quantity;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "items")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<Order> orders = new ArrayList<Order>();

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

	/**
	 * @return List of orders in which the item exists
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            The list of orders in which the item exists
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}

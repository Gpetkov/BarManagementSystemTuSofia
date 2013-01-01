package com.tu.sofia.bg.model;

import java.util.List;

/**
 * This class represent's current customer order
 * 
 * @author GPetkov
 * 
 */
public class Order {
	private Integer id;
	private OrderStatus status;
	private Table table;
	private User waiter;
	private User barman;
	private List<Item> items;

	/**
	 * @return The order's status
	 * 
	 */
	public OrderStatus getStatus() {
		return status;
	}
	/**
	 * @param status
	 *            Order's status
	 * 
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	/**
	 * @return Order's table
	 * 
	 */
	public Table getTable() {
		return table;
	}
	/**
	 * @param table
	 *            Order's table
	 * 
	 */
	public void setTable(Table table) {
		this.table = table;
	}
	/**
	 * @return The order's waiter
	 * 
	 */
	public User getWaiter() {
		return waiter;
	}
	/**
	 * @param waiter
	 *            The order's waiter
	 * 
	 */
	public void setWaiter(User waiter) {
		this.waiter = waiter;
	}
	/**
	 * @return The order's barman
	 * 
	 */
	public User getBarman() {
		return barman;
	}
	/**
	 * @param barman
	 *            The order's barman
	 * 
	 */
	public void setBarman(User barman) {
		this.barman = barman;
	}

	/**
	 * @return The order's items
	 * 
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            The order's items
	 * 
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	/**
	 * @return Order's id
	 * 
	 */
	public Integer getId() {
		return id;
	}
}

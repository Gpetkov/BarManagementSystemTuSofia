package com.tu.sofia.bg.model;

import java.sql.Date;

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
	//private Date createDate;

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
	 * @return The date of create for order
	 * 
	 */
//	public Date getCreateDate() {
//		return createDate;
//	}
//	/**
//	 * @param createDate
//	 *            The date of create for order
//	 * 
//	 */
//	public void setCreateDate(Date createDate) {
//		this.createDate = createDate;
//	}
	/**
	 * @return Order's id
	 * 
	 */
	public Integer getId() {
		return id;
	}
}

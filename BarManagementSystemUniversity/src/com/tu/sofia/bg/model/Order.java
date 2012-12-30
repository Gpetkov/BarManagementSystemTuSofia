package com.tu.sofia.bg.model;

import java.sql.Date;

public class Order {
	private Integer id;
	private OrderStatus status;
	private Table table;
	private User waiter;
	private User barman;
	private Date createDate;
	
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public User getWaiter() {
		return waiter;
	}
	public void setWaiter(User waiter) {
		this.waiter = waiter;
	}
	public User getBarman() {
		return barman;
	}
	public void setBarman(User barman) {
		this.barman = barman;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getId() {
		return id;
	}
}

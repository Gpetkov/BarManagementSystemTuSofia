package com.tu.sofia.bg.model;

import java.sql.Date;

public class Order {
	private OrderStatus status;
	private Table table;
	private User waiter;
	private User barman;
	private Date createDate;
}

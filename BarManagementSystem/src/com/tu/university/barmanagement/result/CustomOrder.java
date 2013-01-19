package com.tu.university.barmanagement.result;

public class CustomOrder extends JsonObject {
	private Integer ordId;
	private Integer orderStatusId;
	private Integer tableId;
	public Integer getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer orderId) {
		this.ordId = orderId;
	}
}

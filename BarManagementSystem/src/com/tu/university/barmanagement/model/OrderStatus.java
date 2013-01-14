package com.tu.university.barmanagement.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The persistent class for the bm_order_status database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_order_status")
@NamedQueries({@NamedQuery(name = "OrderStatus.getAll", query = "Select os from OrderStatus os")})
public class OrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ordst_id")
	private Integer ordstId;

	@Column(name = "ordrst_value")
	private String ordrstValue;

	@Column(name = "ordst_date_created")
	private Timestamp ordstDateCreated;

	@Column(name = "ordst_date_updated")
	private Timestamp ordstDateUpdated;

	// bi-directional many-to-one association to BmOrder
	@OneToMany(mappedBy = "bmOrderStatus")
	private List<Order> bmOrders;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordst_created_by_bm_user_id")
	private User bmUser1;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordst_updated_by_bm_user_id")
	private User bmUser2;

	@PrePersist
	void onCreate() {
		this.setOrdstDateCreated(new Timestamp((new Date()).getTime()));
	}

	@PreUpdate
	void onUpdate() {
		this.setOrdstDateUpdated(new Timestamp((new Date()).getTime()));
	}

	public OrderStatus() {
	}

	public Integer getOrdstId() {
		return this.ordstId;
	}

	public void setOrdstId(Integer ordstId) {
		this.ordstId = ordstId;
	}

	public String getOrdrstValue() {
		return this.ordrstValue;
	}

	public void setOrdrstValue(String ordrstValue) {
		this.ordrstValue = ordrstValue;
	}

	public Timestamp getOrdstDateCreated() {
		return this.ordstDateCreated;
	}

	public void setOrdstDateCreated(Timestamp ordstDateCreated) {
		this.ordstDateCreated = ordstDateCreated;
	}

	public Timestamp getOrdstDateUpdated() {
		return this.ordstDateUpdated;
	}

	public void setOrdstDateUpdated(Timestamp ordstDateUpdated) {
		this.ordstDateUpdated = ordstDateUpdated;
	}

	public List<Order> getBmOrders() {
		return this.bmOrders;
	}

	public void setBmOrders(List<Order> bmOrders) {
		this.bmOrders = bmOrders;
	}

	public User getBmUser1() {
		return this.bmUser1;
	}

	public void setBmUser1(User bmUser1) {
		this.bmUser1 = bmUser1;
	}

	public User getBmUser2() {
		return this.bmUser2;
	}

	public void setBmUser2(User bmUser2) {
		this.bmUser2 = bmUser2;
	}

	public void update(OrderStatus orderStaus) {
		this.setOrdrstValue(orderStaus.getOrdrstValue());
	}
}
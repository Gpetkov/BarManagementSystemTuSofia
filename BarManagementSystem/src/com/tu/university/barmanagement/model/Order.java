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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The persistent class for the bm_order database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ord_id")
	private Integer ordId;

	@Column(name = "ord_date_created")
	private Timestamp ordDateCreated;

	@Column(name = "ord_date_updated")
	private Timestamp ordDateUpdated;

	// bi-directional many-to-one association to BmOrderStatus
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordr_status_id")
	private OrderStatus bmOrderStatus;

	// bi-directional many-to-one association to BmTable
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordr_table_id")
	private Table bmTable;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordr_barman_id")
	private User bmUser1;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ord_created_by_bm_user_id")
	private User bmUser2;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ord_updated_by_bm_user_id")
	private User bmUser3;

	// bi-directional many-to-many association to BmItem
	@ManyToMany
	@JoinTable(name = "bm_order_to_bm_item", joinColumns = {@JoinColumn(name = "ordr_id")}, inverseJoinColumns = {@JoinColumn(name = "itm_id")})
	private List<Item> bmItems;

	@PrePersist
	void onCreate() {
		this.setOrdDateCreated(new Timestamp((new Date()).getTime()));
	}

	@PreUpdate
	void onUpdate() {
		this.setOrdDateUpdated(new Timestamp((new Date()).getTime()));
	}

	public Order() {
	}

	public Integer getOrdId() {
		return this.ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public Timestamp getOrdDateCreated() {
		return this.ordDateCreated;
	}

	public void setOrdDateCreated(Timestamp ordDateCreated) {
		this.ordDateCreated = ordDateCreated;
	}

	public Timestamp getOrdDateUpdated() {
		return this.ordDateUpdated;
	}

	public void setOrdDateUpdated(Timestamp ordDateUpdated) {
		this.ordDateUpdated = ordDateUpdated;
	}

	public OrderStatus getBmOrderStatus() {
		return this.bmOrderStatus;
	}

	public void setBmOrderStatus(OrderStatus bmOrderStatus) {
		this.bmOrderStatus = bmOrderStatus;
	}

	public Table getBmTable() {
		return this.bmTable;
	}

	public void setBmTable(Table bmTable) {
		this.bmTable = bmTable;
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

	public User getBmUser3() {
		return this.bmUser3;
	}

	public void setBmUser3(User bmUser3) {
		this.bmUser3 = bmUser3;
	}

	public List<Item> getBmItems() {
		return this.bmItems;
	}

	public void setBmItems(List<Item> bmItems) {
		this.bmItems = bmItems;
	}

}
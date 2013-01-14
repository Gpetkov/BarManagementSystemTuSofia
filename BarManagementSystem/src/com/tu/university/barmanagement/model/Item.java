package com.tu.university.barmanagement.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The persistent class for the bm_item database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_item")
@NamedQueries({@NamedQuery(name = "Item.getAll", query = "Select i from Item i")})
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itm_id")
	private Integer itmId;

	@Column(name = "itm_date_created")
	private Timestamp itmDateCreated;

	@Column(name = "itm_date_updated")
	private Timestamp itmDateUpdated;

	@Column(name = "itm_name")
	private String itmName;

	@Column(name = "itm_price")
	private double itmPrice;

	@Column(name = "itm_type")
	private String itmType;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "itm_created_by_bm_user_id")
	private User bmUser1;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "itm_updated_by_bm_user_id")
	private User bmUser2;

	// bi-directional many-to-many association to BmOrder
	@ManyToMany(mappedBy = "bmItems",cascade = CascadeType.ALL)
	private List<Order> bmOrders;

	@PrePersist
	void onCreate() {
		this.setItmDateCreated(new Timestamp((new Date()).getTime()));
	}
	@PreUpdate
	void onUpdate() {
		this.setItmDateUpdated(new Timestamp((new Date()).getTime()));
	}
	public Item() {
	}

	public Integer getItmId() {
		return this.itmId;
	}

	public void setItmId(Integer itmId) {
		this.itmId = itmId;
	}

	public Timestamp getItmDateCreated() {
		return this.itmDateCreated;
	}

	public void setItmDateCreated(Timestamp itmDateCreated) {
		this.itmDateCreated = itmDateCreated;
	}

	public Timestamp getItmDateUpdated() {
		return this.itmDateUpdated;
	}

	public void setItmDateUpdated(Timestamp itmDateUpdated) {
		this.itmDateUpdated = itmDateUpdated;
	}

	public String getItmName() {
		return this.itmName;
	}

	public void setItmName(String itmName) {
		this.itmName = itmName;
	}

	public double getItmPrice() {
		return this.itmPrice;
	}

	public void setItmPrice(double itmPrice) {
		this.itmPrice = itmPrice;
	}

	public String getItmType() {
		return this.itmType;
	}

	public void setItmType(String itmType) {
		this.itmType = itmType;
	}

	public User getBmUser1() {
		return this.bmUser1;
	}

	public void setBmUser1(User bmUser1) {
		this.bmUser1 = bmUser1;
	}

	public User getBmUser2() {
//		User bmUser2 = HibernateUtil.unproxy(this.bmUser2);
		return this.bmUser2;
	}

	public void setBmUser2(User bmUser2) {
		this.bmUser2 = bmUser2;
	}

	public List<Order> getBmOrders() {
		return this.bmOrders;
	}

	public void setBmOrders(List<Order> bmOrders) {
		this.bmOrders = bmOrders;
	}

	public void update(Item item) {
		this.setItmName(item.getItmName());
		this.setItmPrice(item.getItmPrice());
		this.setItmType(item.getItmType());
	}
}
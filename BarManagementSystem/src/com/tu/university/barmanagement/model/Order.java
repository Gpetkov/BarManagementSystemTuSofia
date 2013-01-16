package com.tu.university.barmanagement.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The persistent class for the bm_order database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_order")
@NamedQueries({@NamedQuery(name = "Order.getAll", query = "Select o from Order o")})
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordr_status_id")
	private OrderStatus bmOrderStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordr_table_id")
	private Table bmTable;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordr_barman_id")
	private User orderBarman;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordr_created_by_bm_user_id")
	private User userCreated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordr_updated_by_bm_user_id")
	private User userUpdated;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "bm_order_to_bm_item", joinColumns = {@JoinColumn(name = "ordr_id")}, inverseJoinColumns = {@JoinColumn(name = "itm_id")})
	@ElementCollection
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

	public User getOrderBarman() {
		return this.orderBarman;
	}

	public void setOrderBarman(User bmUser1) {
		this.orderBarman = bmUser1;
	}

	public User getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(User bmUser2) {
		this.userCreated = bmUser2;
	}

	public User getUserUpdated() {
		return this.userUpdated;
	}

	public void setUserUpdated(User bmUser3) {
		this.userUpdated = bmUser3;
	}

	public List<Item> getBmItems() {
		return this.bmItems;
	}

	public void setBmItems(List<Item> bmItems) {
		this.bmItems = bmItems;
	}
	public void update(Order order) {
		this.setBmTable(order.getBmTable());
		this.setBmOrderStatus(order.getBmOrderStatus());
		this.setOrderBarman(order.getOrderBarman());
		this.setBmItems(order.getBmItems());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bmItems == null) ? 0 : bmItems.hashCode());
		result = prime * result
				+ ((bmOrderStatus == null) ? 0 : bmOrderStatus.hashCode());
		result = prime * result + ((bmTable == null) ? 0 : bmTable.hashCode());
		result = prime * result
				+ ((ordDateCreated == null) ? 0 : ordDateCreated.hashCode());
		result = prime * result
				+ ((ordDateUpdated == null) ? 0 : ordDateUpdated.hashCode());
		result = prime * result + ((ordId == null) ? 0 : ordId.hashCode());
		result = prime * result
				+ ((orderBarman == null) ? 0 : orderBarman.hashCode());
		result = prime * result
				+ ((userCreated == null) ? 0 : userCreated.hashCode());
		result = prime * result
				+ ((userUpdated == null) ? 0 : userUpdated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (bmItems == null) {
			if (other.bmItems != null)
				return false;
		} else if (!bmItems.equals(other.bmItems))
			return false;
		if (bmOrderStatus == null) {
			if (other.bmOrderStatus != null)
				return false;
		} else if (!bmOrderStatus.equals(other.bmOrderStatus))
			return false;
		if (bmTable == null) {
			if (other.bmTable != null)
				return false;
		} else if (!bmTable.equals(other.bmTable))
			return false;
		if (ordDateCreated == null) {
			if (other.ordDateCreated != null)
				return false;
		} else if (!ordDateCreated.equals(other.ordDateCreated))
			return false;
		if (ordDateUpdated == null) {
			if (other.ordDateUpdated != null)
				return false;
		} else if (!ordDateUpdated.equals(other.ordDateUpdated))
			return false;
		if (ordId == null) {
			if (other.ordId != null)
				return false;
		} else if (!ordId.equals(other.ordId))
			return false;
		if (orderBarman == null) {
			if (other.orderBarman != null)
				return false;
		} else if (!orderBarman.equals(other.orderBarman))
			return false;
		if (userCreated == null) {
			if (other.userCreated != null)
				return false;
		} else if (!userCreated.equals(other.userCreated))
			return false;
		if (userUpdated == null) {
			if (other.userUpdated != null)
				return false;
		} else if (!userUpdated.equals(other.userUpdated))
			return false;
		return true;
	}
	
}
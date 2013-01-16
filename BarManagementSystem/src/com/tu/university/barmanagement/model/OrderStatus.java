package com.tu.university.barmanagement.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordst_created_by_bm_user_id")
	private User userCreated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordst_updated_by_bm_user_id")
	private User userUpdated;

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

	public User getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(User bmUser1) {
		this.userCreated = bmUser1;
	}

	public User getUserUpdated() {
		return this.userUpdated;
	}

	public void setUserUpdated(User bmUser2) {
		this.userUpdated = bmUser2;
	}

	public void update(OrderStatus orderStaus) {
		this.setOrdrstValue(orderStaus.getOrdrstValue());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ordrstValue == null) ? 0 : ordrstValue.hashCode());
		result = prime
				* result
				+ ((ordstDateCreated == null) ? 0 : ordstDateCreated.hashCode());
		result = prime
				* result
				+ ((ordstDateUpdated == null) ? 0 : ordstDateUpdated.hashCode());
		result = prime * result + ((ordstId == null) ? 0 : ordstId.hashCode());
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
		OrderStatus other = (OrderStatus) obj;
		if (ordrstValue == null) {
			if (other.ordrstValue != null)
				return false;
		} else if (!ordrstValue.equals(other.ordrstValue))
			return false;
		if (ordstDateCreated == null) {
			if (other.ordstDateCreated != null)
				return false;
		} else if (!ordstDateCreated.equals(other.ordstDateCreated))
			return false;
		if (ordstDateUpdated == null) {
			if (other.ordstDateUpdated != null)
				return false;
		} else if (!ordstDateUpdated.equals(other.ordstDateUpdated))
			return false;
		if (ordstId == null) {
			if (other.ordstId != null)
				return false;
		} else if (!ordstId.equals(other.ordstId))
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
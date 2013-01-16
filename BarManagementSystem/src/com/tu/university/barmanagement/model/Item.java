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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "itm_created_by_bm_user_id")
	private User userCreated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "itm_updated_by_bm_user_id")
	private User userUpdated;
	
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

	public void update(Item item) {
		this.setItmName(item.getItmName());
		this.setItmPrice(item.getItmPrice());
		this.setItmType(item.getItmType());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itmDateCreated == null) ? 0 : itmDateCreated.hashCode());
		result = prime * result
				+ ((itmDateUpdated == null) ? 0 : itmDateUpdated.hashCode());
		result = prime * result + ((itmId == null) ? 0 : itmId.hashCode());
		result = prime * result + ((itmName == null) ? 0 : itmName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(itmPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((itmType == null) ? 0 : itmType.hashCode());
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
		Item other = (Item) obj;
		if (itmDateCreated == null) {
			if (other.itmDateCreated != null)
				return false;
		} else if (!itmDateCreated.equals(other.itmDateCreated))
			return false;
		if (itmDateUpdated == null) {
			if (other.itmDateUpdated != null)
				return false;
		} else if (!itmDateUpdated.equals(other.itmDateUpdated))
			return false;
		if (itmId == null) {
			if (other.itmId != null)
				return false;
		} else if (!itmId.equals(other.itmId))
			return false;
		if (itmName == null) {
			if (other.itmName != null)
				return false;
		} else if (!itmName.equals(other.itmName))
			return false;
		if (Double.doubleToLongBits(itmPrice) != Double
				.doubleToLongBits(other.itmPrice))
			return false;
		if (itmType == null) {
			if (other.itmType != null)
				return false;
		} else if (!itmType.equals(other.itmType))
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
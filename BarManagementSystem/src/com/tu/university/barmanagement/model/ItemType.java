package com.tu.university.barmanagement.model;

import java.sql.Timestamp;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@javax.persistence.Table(name = "bm_item_type")
@NamedQueries({
		@NamedQuery(name = "ItemType.getAll", query = "Select i from ItemType i"),
		@NamedQuery(name = "ItemType.getByName", query = "SELECT t from ItemType t WHERE t.itmType = :itmName") })
public class ItemType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_type_id")
	private Integer id;

	@Column(name = "item_type_date_created")
	private Timestamp itemTypeDateCreated;

	@Column(name = "item_type_date_updated")
	private Timestamp itmTypeDateUpdated;

	@Column(name = "item_type_name")
	private String itmType;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "item_type_created_by_bm_user_id")
	private User userCreated;

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "item_type_updated_by_bm_user_id")
	private User userUpdated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getItemTypeDateCreated() {
		return itemTypeDateCreated;
	}

	public void setItemTypeDateCreated(Timestamp itemTypeDateCreated) {
		this.itemTypeDateCreated = itemTypeDateCreated;
	}

	public Timestamp getItmTypeDateUpdated() {
		return itmTypeDateUpdated;
	}

	public void setItmTypeDateUpdated(Timestamp itmTypeDateUpdated) {
		this.itmTypeDateUpdated = itmTypeDateUpdated;
	}

	public String getItmType() {
		return itmType;
	}

	public void setItmType(String itmType) {
		this.itmType = itmType;
	}

	public User getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}

	public User getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(User userUpdated) {
		this.userUpdated = userUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((itemTypeDateCreated == null) ? 0 : itemTypeDateCreated
						.hashCode());
		result = prime * result + ((itmType == null) ? 0 : itmType.hashCode());
		result = prime
				* result
				+ ((itmTypeDateUpdated == null) ? 0 : itmTypeDateUpdated
						.hashCode());
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
		ItemType other = (ItemType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemTypeDateCreated == null) {
			if (other.itemTypeDateCreated != null)
				return false;
		} else if (!itemTypeDateCreated.equals(other.itemTypeDateCreated))
			return false;
		if (itmType == null) {
			if (other.itmType != null)
				return false;
		} else if (!itmType.equals(other.itmType))
			return false;
		if (itmTypeDateUpdated == null) {
			if (other.itmTypeDateUpdated != null)
				return false;
		} else if (!itmTypeDateUpdated.equals(other.itmTypeDateUpdated))
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

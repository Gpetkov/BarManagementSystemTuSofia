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
 * The persistent class for the bm_table database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_table")
@NamedQueries({@NamedQuery(name = "Table.getAll", query = "Select t from Table t")})
public class Table implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tbl_id")
	private Integer tblId;

	@Column(name = "tbl_date_created")
	private Timestamp tblDateCreated;

	@Column(name = "tbl_date_updated")
	private Timestamp tblDateUpdated;

	@Column(name = "tbl_number")
	private Integer tblNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_created_by_bm_user_id")
	private User userCreated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_updated_by_bm_user_id")
	private User userUpdated;

	@PrePersist
	void onCreate() {
		this.setTblDateCreated(new Timestamp((new Date()).getTime()));
	}

	@PreUpdate
	void onUpdate() {
		this.setTblDateUpdated(new Timestamp((new Date()).getTime()));
	}

	public Table() {
	}

	public Integer getTblId() {
		return this.tblId;
	}

	public void setTblId(Integer tblId) {
		this.tblId = tblId;
	}

	public Timestamp getTblDateCreated() {
		return this.tblDateCreated;
	}

	public void setTblDateCreated(Timestamp tblDateCreated) {
		this.tblDateCreated = tblDateCreated;
	}

	public Timestamp getTblDateUpdated() {
		return this.tblDateUpdated;
	}

	public void setTblDateUpdated(Timestamp tblDateUpdated) {
		this.tblDateUpdated = tblDateUpdated;
	}

	public Integer getTblNumber() {
		return this.tblNumber;
	}

	public void setTblNumber(Integer tblNumber) {
		this.tblNumber = tblNumber;
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

	public void update(Table table) {
		this.setTblNumber(table.getTblNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tblDateCreated == null) ? 0 : tblDateCreated.hashCode());
		result = prime * result
				+ ((tblDateUpdated == null) ? 0 : tblDateUpdated.hashCode());
		result = prime * result + ((tblId == null) ? 0 : tblId.hashCode());
		result = prime * result
				+ ((tblNumber == null) ? 0 : tblNumber.hashCode());
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
		Table other = (Table) obj;
		if (tblDateCreated == null) {
			if (other.tblDateCreated != null)
				return false;
		} else if (!tblDateCreated.equals(other.tblDateCreated))
			return false;
		if (tblDateUpdated == null) {
			if (other.tblDateUpdated != null)
				return false;
		} else if (!tblDateUpdated.equals(other.tblDateUpdated))
			return false;
		if (tblId == null) {
			if (other.tblId != null)
				return false;
		} else if (!tblId.equals(other.tblId))
			return false;
		if (tblNumber == null) {
			if (other.tblNumber != null)
				return false;
		} else if (!tblNumber.equals(other.tblNumber))
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
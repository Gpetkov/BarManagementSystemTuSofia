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

	// bi-directional many-to-one association to BmOrder
	@OneToMany(mappedBy = "bmTable")
	private List<Order> bmOrders;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_created_by_bm_user_id")
	private User bmUser1;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_updated_by_bm_user_id")
	private User bmUser2;

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

	public void update(Table table) {
		this.setTblNumber(table.getTblNumber());
	}
}
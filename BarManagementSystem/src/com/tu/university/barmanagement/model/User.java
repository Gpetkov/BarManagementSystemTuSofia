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
 * The persistent class for the bm_user database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_user")
@NamedQueries({@NamedQuery(name = "User.getAll", query = "Select u from User u")})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usr_id")
	private Integer usrId;

	@Column(name = "usr_date_created")
	private Timestamp usrDateCreated;

	@Column(name = "usr_date_updated")
	private Timestamp usrDateUpdated;

	@Column(name = "usr_firstname")
	private String usrFirstname;

	@Column(name = "usr_lastname")
	private String usrLastname;

	@Column(name = "usr_passoword")
	private String usrPassoword;

	@Column(name = "usr_role")
	private String usrRole;

	@Column(name = "usr_status")
	private Integer usrStatus;

	@Column(name = "usr_username", unique = true)
	private String usrUsername;

	// bi-directional many-to-one association to BmItem
	@OneToMany(mappedBy = "bmUser1")
	private List<Item> bmItems1;

	// bi-directional many-to-one association to BmItem
	@OneToMany(mappedBy = "bmUser2")
	private List<Item> bmItems2;

	// bi-directional many-to-one association to BmOrder
	@OneToMany(mappedBy = "bmUser1")
	private List<Order> bmOrders1;

	// bi-directional many-to-one association to BmOrder
	@OneToMany(mappedBy = "bmUser2")
	private List<Order> bmOrders2;

	// bi-directional many-to-one association to BmOrder
	@OneToMany(mappedBy = "bmUser3")
	private List<Order> bmOrders3;

	// bi-directional many-to-one association to BmOrderStatus
	@OneToMany(mappedBy = "bmUser1")
	private List<OrderStatus> bmOrderStatuses1;

	// bi-directional many-to-one association to BmOrderStatus
	@OneToMany(mappedBy = "bmUser2")
	private List<OrderStatus> bmOrderStatuses2;

	// bi-directional many-to-one association to BmTable
	@OneToMany(mappedBy = "bmUser1")
	private List<Table> bmTables1;

	// bi-directional many-to-one association to BmTable
	@OneToMany(mappedBy = "bmUser2")
	private List<Table> bmTables2;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usr_created_by_bm_user_id")
	private User bmUser1;

	// bi-directional many-to-one association to BmUser
	@OneToMany(mappedBy = "bmUser1")
	private List<User> bmUsers1;

	// bi-directional many-to-one association to BmUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usr_updated_by_bm_user_id")
	private User bmUser2;

	// bi-directional many-to-one association to BmUser
	@OneToMany(mappedBy = "bmUser2")
	private List<User> bmUsers2;

	@PrePersist
	void onCreate() {
		this.setUsrDateCreated(new Timestamp((new Date()).getTime()));
	}
	@PreUpdate
	void onUpdate() {
		this.setUsrDateUpdated(new Timestamp((new Date()).getTime()));
	}

	public User() {
	}

	public Integer getUsrId() {
		return this.usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public Timestamp getUsrDateCreated() {
		return this.usrDateCreated;
	}

	public void setUsrDateCreated(Timestamp usrDateCreated) {
		this.usrDateCreated = usrDateCreated;
	}

	public Timestamp getUsrDateUpdated() {
		return this.usrDateUpdated;
	}

	public void setUsrDateUpdated(Timestamp usrDateUpdated) {
		this.usrDateUpdated = usrDateUpdated;
	}

	public String getUsrFirstname() {
		return this.usrFirstname;
	}

	public void setUsrFirstname(String usrFirstname) {
		this.usrFirstname = usrFirstname;
	}

	public String getUsrLastname() {
		return this.usrLastname;
	}

	public void setUsrLastname(String usrLastname) {
		this.usrLastname = usrLastname;
	}

	public String getUsrPassoword() {
		return this.usrPassoword;
	}

	public void setUsrPassoword(String usrPassoword) {
		this.usrPassoword = usrPassoword;
	}

	public String getUsrRole() {
		return this.usrRole;
	}

	public void setUsrRole(String usrRole) {
		this.usrRole = usrRole;
	}

	public Integer getUsrStatus() {
		return this.usrStatus;
	}

	public void setUsrStatus(Integer usrStatus) {
		this.usrStatus = usrStatus;
	}

	public String getUsrUsername() {
		return this.usrUsername;
	}

	public void setUsrUsername(String usrUsename) {
		this.usrUsername = usrUsename;
	}

	public List<Item> getBmItems1() {
		return this.bmItems1;
	}

	public void setBmItems1(List<Item> bmItems1) {
		this.bmItems1 = bmItems1;
	}

	public List<Item> getBmItems2() {
		return this.bmItems2;
	}

	public void setBmItems2(List<Item> bmItems2) {
		this.bmItems2 = bmItems2;
	}

	public List<Order> getBmOrders1() {
		return this.bmOrders1;
	}

	public void setBmOrders1(List<Order> bmOrders1) {
		this.bmOrders1 = bmOrders1;
	}

	public List<Order> getBmOrders2() {
		return this.bmOrders2;
	}

	public void setBmOrders2(List<Order> bmOrders2) {
		this.bmOrders2 = bmOrders2;
	}

	public List<Order> getBmOrders3() {
		return this.bmOrders3;
	}

	public void setBmOrders3(List<Order> bmOrders3) {
		this.bmOrders3 = bmOrders3;
	}

	public List<OrderStatus> getBmOrderStatuses1() {
		return this.bmOrderStatuses1;
	}

	public void setBmOrderStatuses1(List<OrderStatus> bmOrderStatuses1) {
		this.bmOrderStatuses1 = bmOrderStatuses1;
	}

	public List<OrderStatus> getBmOrderStatuses2() {
		return this.bmOrderStatuses2;
	}

	public void setBmOrderStatuses2(List<OrderStatus> bmOrderStatuses2) {
		this.bmOrderStatuses2 = bmOrderStatuses2;
	}

	public List<Table> getBmTables1() {
		return this.bmTables1;
	}

	public void setBmTables1(List<Table> bmTables1) {
		this.bmTables1 = bmTables1;
	}

	public List<Table> getBmTables2() {
		return this.bmTables2;
	}

	public void setBmTables2(List<Table> bmTables2) {
		this.bmTables2 = bmTables2;
	}

	public User getBmUser1() {
		return this.bmUser1;
	}

	public void setBmUser1(User bmUser1) {
		this.bmUser1 = bmUser1;
	}

	public List<User> getBmUsers1() {
		return this.bmUsers1;
	}

	public void setBmUsers1(List<User> bmUsers1) {
		this.bmUsers1 = bmUsers1;
	}

	public User getBmUser2() {
		return this.bmUser2;
	}

	public void setBmUser2(User bmUser2) {
		this.bmUser2 = bmUser2;
	}

	public List<User> getBmUsers2() {
		return this.bmUsers2;
	}

	public void setBmUsers2(List<User> bmUsers2) {
		this.bmUsers2 = bmUsers2;
	}
	
	public void update(User user)
	{
		this.setUsrUsername(user.getUsrUsername());
		this.setUsrFirstname(user.getUsrFirstname());
		this.setUsrLastname(user.getUsrLastname());
		this.setUsrPassoword(user.getUsrPassoword());
		this.setUsrRole(user.getUsrRole());
		this.setUsrStatus(user.getUsrStatus());
	}

}
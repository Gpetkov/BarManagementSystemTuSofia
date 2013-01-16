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
 * The persistent class for the bm_user database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_user")
@NamedQueries({
		@NamedQuery(name = "User.getAll", query = "Select u from User u"),
		@NamedQuery(name = "User.getByUserName", query = "SELECT u from User u WHERE u.usrUsername = :usrUsername")})
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

	@Column(name = "usr_password")
	private String usrPassword;

	@Column(name = "usr_role")
	private String usrRole;

	@Column(name = "usr_status")
	private Integer usrStatus;

	@Column(name = "usr_username", unique = true)
	private String usrUsername;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_created_by_bm_user_id")
	private User userCreated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_updated_by_bm_user_id")
	private User userUpdated;

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

	public String getUsrPassword() {
		return this.usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
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

	public void update(User user) {
			this.setUsrUsername(user.getUsrUsername());
			this.setUsrFirstname(user.getUsrFirstname());
			this.setUsrLastname(user.getUsrLastname());
			this.setUsrPassword(user.getUsrPassword());
			this.setUsrRole(user.getUsrRole());
			this.setUsrStatus(user.getUsrStatus());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userCreated == null) ? 0 : userCreated.hashCode());
		result = prime * result
				+ ((userUpdated == null) ? 0 : userUpdated.hashCode());
		result = prime * result
				+ ((usrDateCreated == null) ? 0 : usrDateCreated.hashCode());
		result = prime * result
				+ ((usrDateUpdated == null) ? 0 : usrDateUpdated.hashCode());
		result = prime * result
				+ ((usrFirstname == null) ? 0 : usrFirstname.hashCode());
		result = prime * result + ((usrId == null) ? 0 : usrId.hashCode());
		result = prime * result
				+ ((usrLastname == null) ? 0 : usrLastname.hashCode());
		result = prime * result
				+ ((usrPassword == null) ? 0 : usrPassword.hashCode());
		result = prime * result + ((usrRole == null) ? 0 : usrRole.hashCode());
		result = prime * result
				+ ((usrStatus == null) ? 0 : usrStatus.hashCode());
		result = prime * result
				+ ((usrUsername == null) ? 0 : usrUsername.hashCode());
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
		User other = (User) obj;
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
		if (usrDateCreated == null) {
			if (other.usrDateCreated != null)
				return false;
		} else if (!usrDateCreated.equals(other.usrDateCreated))
			return false;
		if (usrDateUpdated == null) {
			if (other.usrDateUpdated != null)
				return false;
		} else if (!usrDateUpdated.equals(other.usrDateUpdated))
			return false;
		if (usrFirstname == null) {
			if (other.usrFirstname != null)
				return false;
		} else if (!usrFirstname.equals(other.usrFirstname))
			return false;
		if (usrId == null) {
			if (other.usrId != null)
				return false;
		} else if (!usrId.equals(other.usrId))
			return false;
		if (usrLastname == null) {
			if (other.usrLastname != null)
				return false;
		} else if (!usrLastname.equals(other.usrLastname))
			return false;
		if (usrPassword == null) {
			if (other.usrPassword != null)
				return false;
		} else if (!usrPassword.equals(other.usrPassword))
			return false;
		if (usrRole == null) {
			if (other.usrRole != null)
				return false;
		} else if (!usrRole.equals(other.usrRole))
			return false;
		if (usrStatus == null) {
			if (other.usrStatus != null)
				return false;
		} else if (!usrStatus.equals(other.usrStatus))
			return false;
		if (usrUsername == null) {
			if (other.usrUsername != null)
				return false;
		} else if (!usrUsername.equals(other.usrUsername))
			return false;
		return true;
	}

}
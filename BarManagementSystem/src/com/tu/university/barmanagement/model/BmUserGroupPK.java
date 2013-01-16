package com.tu.university.barmanagement.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the bm_user_group database table.
 * 
 */
@Embeddable
public class BmUserGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_username")
	private String userUsername;

	@Column(name="group_name")
	private String groupName;

	public BmUserGroupPK() {
	}
	public String getUserUsername() {
		return this.userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public String getGroupName() {
		return this.groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BmUserGroupPK)) {
			return false;
		}
		BmUserGroupPK castOther = (BmUserGroupPK)other;
		return 
			this.userUsername.equals(castOther.userUsername)
			&& this.groupName.equals(castOther.groupName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userUsername.hashCode();
		hash = hash * prime + this.groupName.hashCode();
		
		return hash;
	}
}
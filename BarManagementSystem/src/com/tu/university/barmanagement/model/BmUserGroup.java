package com.tu.university.barmanagement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bm_user_group database table.
 * 
 */
@Entity
@javax.persistence.Table(name="bm_user_group")
public class BmUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BmUserGroupPK id;

	public BmUserGroup() {
	}

	public BmUserGroupPK getId() {
		return this.id;
	}

	public void setId(BmUserGroupPK id) {
		this.id = id;
	}

}
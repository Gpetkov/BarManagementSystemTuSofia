package com.tu.university.barmanagement.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

/**
 * This class represent's current table for customer order
 * 
 * @author GPetkov
 * 
 */
@Entity
@javax.persistence.Table(name = "TABLE")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "TBL_ID")),
		@AttributeOverride(name = "dateCreated", column = @Column(name = "TBL_DATE_CREATED")),
		@AttributeOverride(name = "dateUpdated", column = @Column(name = "TBL_DATE_UPDATED"))})
@AssociationOverrides({
		@AssociationOverride(name = "createdByUser", joinColumns = @JoinColumn(name = "TBL_CREATED_BY_USER_ID")),
		@AssociationOverride(name = "updatedByUser", joinColumns = @JoinColumn(name = "TBL_UPDATED_BY_USER_ID"))})
public class Table extends ModelBase implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "TBL_NUMBER", nullable = false)
	private Integer number;

	/**
	 * @return Table's number
	 * 
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            Table's number
	 * 
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	// /**
	// * @return Table's id
	// *
	// */
	// public Integer getId() {
	// return id;
	// }

}

package com.tu.sofia.bg.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * This class represent's the status for current customer order
 * 
 * @author GPetkov
 * 
 */
@Entity
@Table(name = "ORDER_STATUS")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "ORDST_ID")),
		@AttributeOverride(name = "dateCreated", column = @Column(name = "ORDST_DATE_CREATED")),
		@AttributeOverride(name = "dateUpdated", column = @Column(name = "ORDST_DATE_UPDATED"))})
@AssociationOverrides({
		@AssociationOverride(name = "createdByUser", joinColumns = @JoinColumn(name = "ORDST_CREATED_BY_USER_ID")),
		@AssociationOverride(name = "updatedByUser", joinColumns = @JoinColumn(name = "ORDST_UPDATED_BY_USER_ID"))})
public class OrderStatus extends ModelBase implements Serializable {
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	// private Integer id;
	@Column(name = "ORDRST_VALUE", nullable = false, length = 50)
	private String value;

	/**
	 * @return Status value
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            Status value
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	// /**
	// * @return Status id
	// *
	// */
	// public Integer getId() {
	// return id;
	// }
}

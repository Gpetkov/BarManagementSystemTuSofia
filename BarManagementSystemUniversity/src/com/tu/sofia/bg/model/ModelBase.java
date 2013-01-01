package com.tu.sofia.bg.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * This class represents a root object of the class hierarchy
 * 
 */
@MappedSuperclass
@EntityListeners({ModelListener.class})
public abstract class ModelBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Version
	@Column(name = "version")
	private Long version = 1L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "created_by_user_id")
	private User createdByUser;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by_user_id ")
	private User updatedByUser;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "date_created")
	private Date dateCreated;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "date_updated")
	private Date dateUpdated;

	/**
	 * @return current id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            currentId
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Current Version
	 * 
	 */
	public Long getVersion() {
		return version;
	}

	// /**
	// * @param version
	// * Current Version
	// *
	// */
	// public void setVersion(Long version) {
	// this.version = version;
	// }

	/**
	 * @return The user created the object
	 * 
	 */
	public User getCreatedByUser() {
		return createdByUser;
	}

	/**
	 * @param createdByUser
	 *            The user created the object
	 * 
	 */
	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	/**
	 * @return The user updated the object
	 * 
	 */
	public User getUpdatedByUser() {
		return updatedByUser;
	}

	/**
	 * @param updatedByUser
	 *            The user updated the object
	 * 
	 */
	public void setUpdatedByUser(User updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	/**
	 * @return The date of create
	 * 
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            The date of create
	 * 
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return The date of update
	 * 
	 */
	public Date getDateUpdated() {
		return dateUpdated;
	}

	/**
	 * @param dateUpdated
	 *            The date of update
	 * 
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

}

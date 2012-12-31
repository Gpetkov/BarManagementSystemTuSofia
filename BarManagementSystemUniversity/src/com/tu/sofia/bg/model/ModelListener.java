package com.tu.sofia.bg.model;

import java.util.Date;

import javax.persistence.PrePersist;

public class ModelListener {
	@PrePersist
	public void setDatesAndUser(ModelBase modelBase) {
		// Set CreatedBy and UpdatedBy User information
		User currentUser = UserUtil.getCurrentUser();
		// check to see if modelBase and currentUser are the same, if so, make
		// currentUser modelBase
		if (modelBase.equals(currentUser)) {
			currentUser = (User) modelBase;
		}

		if (currentUser != null) {
			if (modelBase.getCreatedByUser() == null) {
				modelBase.setCreatedByUser(currentUser);
			}
			modelBase.setUpdatedByUser(currentUser);
		}

		// set dateCreated and dateUpdated fields
		Date now = new Date();

		if (modelBase.getDateCreated() == null) {
			modelBase.setDateCreated(now);
		}
		modelBase.setDateUpdated(now);
	}
}

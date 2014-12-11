package com.tu.university.barmanagement.model;

public class ItemDTO {
	private Integer itmType;
	private String itmName;
	private Double itmPrice;
	public Integer getItmType() {
		return itmType;
	}
	public void setItmType(Integer itmType) {
		this.itmType = itmType;
	}
	public String getItmName() {
		return itmName;
	}
	public void setItmName(String itmName) {
		this.itmName = itmName;
	}
	public Double getItmPrice() {
		return itmPrice;
	}
	public void setItmPrice(Double itmPrice) {
		this.itmPrice = itmPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itmName == null) ? 0 : itmName.hashCode());
		result = prime * result
				+ ((itmPrice == null) ? 0 : itmPrice.hashCode());
		result = prime * result + ((itmType == null) ? 0 : itmType.hashCode());
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
		ItemDTO other = (ItemDTO) obj;
		if (itmName == null) {
			if (other.itmName != null)
				return false;
		} else if (!itmName.equals(other.itmName))
			return false;
		if (itmPrice == null) {
			if (other.itmPrice != null)
				return false;
		} else if (!itmPrice.equals(other.itmPrice))
			return false;
		if (itmType == null) {
			if (other.itmType != null)
				return false;
		} else if (!itmType.equals(other.itmType))
			return false;
		return true;
	}
}

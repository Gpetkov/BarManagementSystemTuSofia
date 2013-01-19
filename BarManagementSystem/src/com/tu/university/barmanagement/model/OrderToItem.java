package com.tu.university.barmanagement.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the bm_order_to_bm_item database table.
 * 
 */
@Entity
@javax.persistence.Table(name = "bm_order_to_bm_item")
@NamedQueries({@NamedQuery(name = "OrderToItem.getAll", query = "Select oti from OrderToItem oti ")})
public class OrderToItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_to_item_id")
	private Integer orderToItemId;

	@Column(name = "itm_id")
	private Integer itmId;

	@Column(name = "ordr_id")
	private Integer ordrId;

	public OrderToItem() {
	}

	public Integer getOrderToItemId() {
		return this.orderToItemId;
	}

	public void setOrderToItemId(Integer orderToItemId) {
		this.orderToItemId = orderToItemId;
	}

	public Integer getItmId() {
		return this.itmId;
	}

	public void setItmId(Integer itmId) {
		this.itmId = itmId;
	}

	public Integer getOrdrId() {
		return this.ordrId;
	}

	public void setOrdrId(Integer ordrId) {
		this.ordrId = ordrId;
	}

	public void update(OrderToItem ordrToItem) {
		this.setItmId(ordrToItem.getItmId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itmId == null) ? 0 : itmId.hashCode());
		result = prime * result
				+ ((orderToItemId == null) ? 0 : orderToItemId.hashCode());
		result = prime * result + ((ordrId == null) ? 0 : ordrId.hashCode());
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
		OrderToItem other = (OrderToItem) obj;
		if (itmId == null) {
			if (other.itmId != null)
				return false;
		} else if (!itmId.equals(other.itmId))
			return false;
		if (orderToItemId == null) {
			if (other.orderToItemId != null)
				return false;
		} else if (!orderToItemId.equals(other.orderToItemId))
			return false;
		if (ordrId == null) {
			if (other.ordrId != null)
				return false;
		} else if (!ordrId.equals(other.ordrId))
			return false;
		return true;
	}

}
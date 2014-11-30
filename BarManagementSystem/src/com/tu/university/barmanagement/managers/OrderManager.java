package com.tu.university.barmanagement.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.Order;
import com.tu.university.barmanagement.model.OrderStatus;
import com.tu.university.barmanagement.model.Table;
import com.tu.university.barmanagement.model.User;

@Stateless
public class OrderManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders() {
		return em.createNamedQuery("Order.getAll").getResultList();
	}

	public Order getOrderById(Integer id) {
		Order order = em.find(Order.class, id);
		em.flush();
		return order;
	}

	public Integer addOrder(Order order) {
		em.persist(order);
		em.flush();
		return order.getOrdId();
	}

	public void updateOrder(Order order) {

		em.merge(order);
		em.flush();
	}

	public void deleteOrderById(Integer id) {
		Order order = this.getOrderById(id);
		if (order != null)
			em.remove(order);
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrdersWithoutBarman() {
		javax.persistence.Query q = em
				.createNamedQuery("findOrdersWithoutBarman");
		// q = q.setParameter(1, null);
		List<Order> result = q.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrdersWithStatusNotFinished() {
		List<Order> result = new ArrayList<Order>();
		javax.persistence.Query q = em
				.createNativeQuery("Select o.ord_id, t.t.tbl_number, o.ordr_status_id from bm_order o left join bm_order_status "
						+ "os on o.ordr_status_id = os.ordst_id left join bm_table t on o.ordr_table_id=t.tbl_id where os.ordst_id != 1");

		List<Object[]> resultList = q.getResultList();
		for (Object[] current : resultList) {
			Order order = new Order();
			order.setOrdId(Integer.valueOf(current[0].toString()));
			Table table = new Table();
			table.setTblNumber(Integer.valueOf(current[1].toString()));
			order.setBmTable(table);
			OrderStatus orderStatus = new OrderStatus();
			orderStatus.setOrdstId(Integer.valueOf(current[2].toString()));
			order.setBmOrderStatus(orderStatus);
			result.add(order);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllBarmanOrders(User usr) {
		javax.persistence.Query q = em.createNamedQuery("findBarmanOrders");
		// q = q.setParameter(1, null);
		q.setParameter("ordbarman", usr);
		List<Order> result = q.getResultList();
		return result;
	}
}

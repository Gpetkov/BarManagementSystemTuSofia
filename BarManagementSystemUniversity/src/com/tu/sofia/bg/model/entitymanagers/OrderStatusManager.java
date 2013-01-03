package com.tu.sofia.bg.model.entitymanagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.tu.sofia.bg.model.OrderStatus;

public class OrderStatusManager
{
    private EntityManager em;

    public OrderStatusManager(EntityManagerFactory emf)
    {
        em = emf.createEntityManager();
    }

    /**
     * Method used to create order status.
     * 
     * @param orderStatus Current order status which have to be store in a database
     */
    public void createOrderStatus(OrderStatus orderStatus)
    {
        System.out.println("Creation");
        em.getTransaction().begin();
        // Persist entity in persistence context
        em.persist(orderStatus);
        // Commit Transaction
        em.getTransaction().commit();
        System.out.println("Current Status was created successfully!");
    }

    /**
     * Method used for search an order status by id
     * 
     * @param id Current orderstatus's id
     */
    public OrderStatus searchOrderStatusById(Integer id)
    {
        // Method used to find order status by id
        return em.find(OrderStatus.class, id);
    }

    /**
     * Method used for updating the order status
     * 
     * @param status Current order status
     */
    public void updateOrderStatus(OrderStatus status)
    {
        System.out.println("Update: ");
        em.getTransaction().begin();
        em.merge(status);
        em.getTransaction().commit();
        System.out.println("Updated successfully");
    }

    /**
     * Method used for removing the order status
     * 
     * @param user Current user
     */
    public void removeOrderStatus(OrderStatus status)
    {
        System.out.println("Remove: ");
        em.getTransaction().begin();
        em.remove(status);
        em.getTransaction().commit();
        System.out.println("User removed successfully");
    }

    /**
     * Method used for getting all rows in ORDER_STATUS table
     * 
     */
    @SuppressWarnings("rawtypes")
    public List getAllFromOrderStatus()
    {
        Query query = em.createQuery("select os from OrderStatus os");

        List result = query.getResultList();

        return result;
    }

    /**
     * Method used for closing the entity manager
     * 
     */
    public void close()
    {
        em.close();
    }
}

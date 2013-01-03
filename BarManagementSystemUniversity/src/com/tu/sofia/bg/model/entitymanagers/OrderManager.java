package com.tu.sofia.bg.model.entitymanagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.tu.sofia.bg.model.Order;

public class OrderManager
{
    private EntityManager em;

    public OrderManager(EntityManagerFactory emf)
    {
        em = emf.createEntityManager();
    }

    /**
     * Method used to create Order.
     * 
     * @param Order Current Order which have to be store in a database
     */
    public void createOrder(Order order)
    {
        System.out.println("Creation");
        em.getTransaction().begin();
        // Persist entity in persistence context
        em.persist(order);
        // Commit Transaction
        em.getTransaction().commit();
        System.out.println("Order created successfully");
    }

    /**
     * Method used for search Order by id
     * 
     * @param id Current Order's id
     */
    public Order searchById(Integer id)
    {
        // Method used to find data
        return em.find(Order.class, id);
    }

    /**
     * Method used for updating the current Order
     * 
     * @param Order Current Order
     */
    public void updateOrder(Order order)
    {
        System.out.println("Update: ");
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
        System.out.println("Updated successfully");
    }

    /**
     * Method used for removing the current Order
     * 
     * @param order Current Order
     */
    public void removeOrder(Order order)
    {
        System.out.println("Remove: ");
        em.getTransaction().begin();
        em.remove(order);
        em.getTransaction().commit();
        System.out.println("Removed successfully");
    }

    /**
     * Method used for getting all Orders
     * 
     */
    @SuppressWarnings("rawtypes")
    public List getAllOrders()
    {
        Query query = em.createQuery("select i from Order i");
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

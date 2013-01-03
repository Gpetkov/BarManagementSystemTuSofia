package com.tu.sofia.bg.model.entitymanagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.tu.sofia.bg.model.Item;

public class ItemManager
{
    private EntityManager em;

    public ItemManager(EntityManagerFactory emf)
    {
        em = emf.createEntityManager();
    }

    /**
     * Method used to create item.
     * 
     * @param item Current item which have to be store in a database
     */
    public void createItem(Item item)
    {
        System.out.println("Creation");
        em.getTransaction().begin();
        // Persist entity in persistence context
        em.persist(item);
        // Commit Transaction
        em.getTransaction().commit();
        System.out.println("Item created successfully");
    }

    /**
     * Method used for search item by id
     * 
     * @param id Current item's id
     */
    public Item searchById(Integer id)
    {
        // Method used to find data
        return em.find(Item.class, id);
    }

    /**
     * Method used for updating the current item
     * 
     * @param item Current item
     */
    public void updateItem(Item item)
    {
        System.out.println("Update: ");
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
        System.out.println("Updated successfully");
    }

    /**
     * Method used for removing the current item
     * 
     * @param item Current item
     */
    public void removeItem(Item item)
    {
        System.out.println("Remove: ");
        em.getTransaction().begin();
        em.remove(item);
        em.getTransaction().commit();
        System.out.println("Removed successfully");
    }

    /**
     * Method used for getting all items
     * 
     */
    @SuppressWarnings("rawtypes")
    public List getAllItems()
    {
        Query query = em.createQuery("select i from Item i");
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

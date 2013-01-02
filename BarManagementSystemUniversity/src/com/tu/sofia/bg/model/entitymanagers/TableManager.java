package com.tu.sofia.bg.model.entitymanagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.tu.sofia.bg.model.Table;

public class TableManager
{
    private EntityManager em;

    public TableManager(EntityManagerFactory emf)
    {
        em = emf.createEntityManager();
    }

    /**
     * Method used to create table.
     * 
     * @param table Current table which have to be store in a database
     */
    public void createTable(Table table)
    {
        System.out.println("Creation");
        em.getTransaction().begin();
        // Persist entity in persistence context
        em.persist(table);
        // Commit Transaction
        em.getTransaction().commit();
        System.out.println("Table created successfully");
    }

    /**
     * Method used for search table number by id
     * 
     * @param id Current table's id
     */
    public Table searchById(Integer id)
    {
        // Method used to find data
        return em.find(Table.class, id);
    }

    /**
     * Method used for updating the current table
     * 
     * @param table Current table
     */
    public void updateTable(Table table)
    {
        System.out.println("Update: ");
        em.getTransaction().begin();
        em.merge(table);
        em.getTransaction().commit();
        System.out.println("Updated successfully");
    }

    /**
     * Method used for removing the current table
     * 
     * @param table Current table
     */
    public void removeTable(Table table)
    {
        System.out.println("Remove: ");
        em.getTransaction().begin();
        em.remove(table);
        em.getTransaction().commit();
        System.out.println("Removed successfully");
    }

    /**
     * Method used for getting all tables
     * 
     */
    @SuppressWarnings("rawtypes")
    public List getAllTables(Table table)
    {
        Query query = em.createQuery("select a from TABLE a");

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

package com.tu.sofia.bg.model.entitymanagers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.tu.sofia.bg.model.User;

public class UserManager
{
    private EntityManager em;

    public UserManager(EntityManagerFactory emf)
    {
        em = emf.createEntityManager();
    }

    /**
     * Method used to create table.
     * 
     * @param user Current user which have to be store in a database
     */
    public void createUser(User user)
    {
        System.out.println("Creation");
        em.getTransaction().begin();
        // Persist entity in persistence context
        em.persist(user);
        // Commit Transaction
        em.getTransaction().commit();
        System.out.println("User created successfully");
    }

    /**
     * Method used for search an user by id
     * 
     * @param id Current user's id
     */
    public User searchUserById(Integer id)
    {
        // Method used to find user by id
        return em.find(User.class, id);
    }

    /**
     * Method used for updating the User
     * 
     * @param user Current user
     */
    public void updateUser(User user)
    {
        System.out.println("Update: ");
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        System.out.println("Updated successfully");
    }

    /**
     * Method used for removing the User
     * 
     * @param user Current user
     */
    public void removeUser(User user)
    {
        System.out.println("Remove: ");
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        System.out.println("User removed successfully");
    }

    /**
     * Method used for getting all Users
     * 
     */
    @SuppressWarnings("rawtypes")
    public List getAllUsers(User user)
    {
        Query query = em.createQuery("select u from User u");

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

package com.tu.sofia.bg.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * This class represent's a current User in the system
 * 
 * @author GPetkov
 * 
 */
@Entity
@Table(name = "USER")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "USR_ID")),
        @AttributeOverride(name = "dateCreated", column = @Column(name = "USR_DATE_CREATED")),
        @AttributeOverride(name = "dateUpdated", column = @Column(name = "USR_DATE_UPDATED")) })
@AssociationOverrides({ @AssociationOverride(name = "createdByUser", joinColumns = @JoinColumn(name = "USR_CREATED_BY_USER_ID")),
        @AssociationOverride(name = "updatedByUser", joinColumns = @JoinColumn(name = "USR_UPDATED_BY_USER_ID")) })
public class User extends ModelBase
{
    // private Integer id;
    @Column(name = "USR_FIRSTNAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "USR_LASTNAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "USR_USENAME", nullable = false, length = 50)
    private String userName;
    @Column(name = "USR_PASSOWORD", nullable = false, length = 100)
    private String password;
    @Column(name = "USR_ROLE", nullable = false, length = 10)
    private String role;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedByUser", orphanRemoval = true)
    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    private List<Order> orders = new ArrayList<Order>();
    @Column(name = "USR_STATUS", nullable = false, length = 1)
    private Integer userStatus;

    /**
     * @return User's first name
     * 
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName User's first name
     * 
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return User's second name
     * 
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName User's last name
     * 
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return User's user name
     * 
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName User's user name
     * 
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return User's password
     * 
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password User's password
     * 
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return User's role
     * 
     */
    public String getRole()
    {
        return role;
    }

    /**
     * @param role User's role
     * 
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * @return User's orders
     * 
     */
    public List<Order> getOrders()
    {
        return orders;
    }

    /**
     * @param orders User's orders
     * 
     */
    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    /**
     * @return The user's status(active->0 else->1)
     * 
     */
    public Integer getUserStatus()
    {
        return userStatus;
    }

    /**
     * @param userStatus The user's status(active->0 else->1)
     * 
     */
    public void setUserStatus(Integer userStatus)
    {
        this.userStatus = userStatus;
    }
    // /**
    // * @return User's id
    // *
    // */
    // public Integer getId() {
    // return id;
    // }
}

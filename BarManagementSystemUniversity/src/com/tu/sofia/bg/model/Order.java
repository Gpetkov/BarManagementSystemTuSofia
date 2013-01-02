package com.tu.sofia.bg.model;

import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * This class represent's current customer order
 * 
 * @author GPetkov
 * 
 */
@Entity
@Table(name = "ORDER")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "ORD_ID")),
        @AttributeOverride(name = "dateCreated", column = @Column(name = "ORD_DATE_CREATED")),
        @AttributeOverride(name = "dateUpdated", column = @Column(name = "ORD_DATE_UPDATED")) })
@AssociationOverrides({ @AssociationOverride(name = "createdByUser", joinColumns = @JoinColumn(name = "ORD_CREATED_BY_USER_ID")),
        @AssociationOverride(name = "updatedByUser", joinColumns = @JoinColumn(name = "ORD_UPDATED_BY_USER_ID")) })
public class Order
{
    // private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDR_STATUS_ID")
    private OrderStatus status;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDR_TABLE_ID")
    private Table table;
    // private User waiter;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDR_BARMAN_ID")
    private User barman;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    private List<Item> items;

    /**
     * @return The order's status
     * 
     */
    public OrderStatus getStatus()
    {
        return status;
    }

    /**
     * @param status Order's status
     * 
     */
    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }

    /**
     * @return Order's table
     * 
     */
    public Table getTable()
    {
        return table;
    }

    /**
     * @param table Order's table
     * 
     */
    public void setTable(Table table)
    {
        this.table = table;
    }

    // /**
    // * @return The order's waiter
    // *
    // */
    // public User getWaiter() {
    // return waiter;
    // }
    // /**
    // * @param waiter
    // * The order's waiter
    // *
    // */
    // public void setWaiter(User waiter) {
    // this.waiter = waiter;
    // }
    /**
     * @return The order's barman
     * 
     */
    public User getBarman()
    {
        return barman;
    }

    /**
     * @param barman The order's barman
     * 
     */
    public void setBarman(User barman)
    {
        this.barman = barman;
    }

    /**
     * @return The order's items
     * 
     */
    public List<Item> getItems()
    {
        return items;
    }

    /**
     * @param items The order's items
     * 
     */
    public void setItems(List<Item> items)
    {
        this.items = items;
    }
    // /**
    // * @return Order's id
    // *
    // */
    // public Integer getId() {
    // return id;
    // }
}

package com.tu.sofia.bg.dummy;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tu.sofia.bg.model.OrderStatus;
import com.tu.sofia.bg.model.entitymanagers.OrderStatusManager;

public class TestHibernate
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OrderStatus");
        OrderStatusManager orderStatusManager = new OrderStatusManager(emf);

        // Table table = new Table();
        // table.setNumber(1);
        OrderStatus o = new OrderStatus();
        o.setValue("sadsa");
        // orderStatusManager.createOrderStatus(o);
       // orderStatusManager.removeOrderStatus(orderStatusManager.searchOrderStatusById(3));
        List<OrderStatus> l = orderStatusManager.getAllFromOrderStatus();
        for(OrderStatus p : l)
        {
            System.out.println(p);
        }
        emf.close();
        //orderStatusManager.close();
    }
}

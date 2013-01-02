package com.tu.sofia.bg.dummy;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tu.sofia.bg.model.Table;
import com.tu.sofia.bg.model.entitymanagers.TableManager;

public class TestHibernate
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TABLE");
        TableManager tableManager = new TableManager(emf);

        Table table = new Table();
        table.setNumber(1);

        tableManager.createTable(table);
        emf.close();
    }
}

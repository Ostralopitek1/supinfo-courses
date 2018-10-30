package com.supinfo.supcommerce.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static final PersistenceManager INSTANCE = new PersistenceManager();
    private static EntityManagerFactory emf;

    public static PersistenceManager getInstance() {
        return INSTANCE;
    }

    public static EntityManagerFactory getEntityManagerFactory () {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if(emf != null && emf.isOpen()) emf.close();
    }
}

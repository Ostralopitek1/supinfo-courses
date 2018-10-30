package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.dao.jpa.JpaCategoryDao;
import com.supinfo.supcommerce.dao.jpa.JpaProductDao;
import com.supinfo.supcommerce.util.PersistenceManager;

import javax.persistence.EntityManagerFactory;

public final class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private final EntityManagerFactory emf;

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    private DaoFactory() {
        emf = PersistenceManager.getInstance().getEntityManagerFactory();
    }

    public CategoryDao getCategoryDao() {
        return new JpaCategoryDao(emf);
    }

    public ProductDao getProductDao() {
        return new JpaProductDao(emf);
    }
}

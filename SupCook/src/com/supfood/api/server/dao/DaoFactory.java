package com.supfood.api.server.dao;

import com.supfood.api.server.dao.jpa.*;

import javax.persistence.EntityManagerFactory;


public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    private final EntityManagerFactory emf;

    private DaoFactory() {
        emf = PersistenceManager.getInstance().getEntityManagerFactory();
    }

    public CategoryDao getCategoryDao() {
        return new JpaCategoryDao(emf);
    }

    public CommonDao getCommonDao() {
        return new JpaCommonDao(emf);
    }

    public MarkDao getMarkDao() {
        return new JpaMarkDao(emf);
    }

    public PictureDao getPictureDao() {
        return new JpaPictureDao(emf);
    }

    public ProductDao getProductDao() {
        return new JpaProductDao(emf);
    }

    public RecipeDao getRecipeDao() {
        return new JpaRecipeDao(emf);
    }

    public UserDao getUserDao() {
        return new JpaUserDao(emf);
    }

}

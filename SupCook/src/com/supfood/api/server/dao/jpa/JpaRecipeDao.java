package com.supfood.api.server.dao.jpa;

import com.supfood.api.server.dao.*;
import com.supfood.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JpaRecipeDao implements RecipeDao {

    private final EntityManagerFactory emf;


    public JpaRecipeDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        JpaObjectDao.addObject(emf, recipe, recipe.getId(), Recipe.class);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        JpaObjectDao.updateObject(emf, recipe, recipe.getId(), Recipe.class);
    }

    @Override
    public void removeRecipeById(int id) {
        Recipe recipe = (Recipe) JpaObjectDao.getObject(emf, id, Recipe.class);
        removeRecipe(recipe);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        //removeChildrens(recipe);
        JpaObjectDao.removeObject(emf, recipe);
    }

    public void removeChildrens(Recipe recipe) {
        // Remove products
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        List<Product> products = productDao.getRecipeProducts(recipe);
        for (Product product : products) {
            productDao.removeProduct(product);
        }

        // Remove pictures
        PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
        List<Picture> pictures = pictureDao.getRecipePictures(recipe);
        for (Picture picture : pictures) {
            //pictureDao.removePicture(picture);
            recipe.removePicture(picture);
        }

        // Remove marks
        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        List<Mark> marks = markDao.getRecipeMarks(recipe);
        for (Mark mark : marks) {
            markDao.removeMark(mark);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<Recipe>();
        EntityManager em = emf.createEntityManager();
        try {
            result.addAll(em.createQuery("SELECT r FROM Recipe r WHERE active=true").getResultList());
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public Recipe getRecipe(int id) {
        Object object = JpaObjectDao.getObject(emf, id, Recipe.class);
        if (object == null) {
            return null;
        }
        return (Recipe) object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Recipe> getUserRecipes(User user) {
        List<Recipe> result = new ArrayList<Recipe>();
        EntityManager em = emf.createEntityManager();
        try {
            Query sqlQuery = em.createQuery("SELECT r FROM Recipe r WHERE owner=:user AND active=true");
            sqlQuery.setParameter("user", user);
            result.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Recipe> searchRecipes(String query) {
        EntityManager em = emf.createEntityManager();

        ArrayList<Recipe> results = new ArrayList<Recipe>();
        try {
            Query sqlQuery = em.createQuery("SELECT r FROM Recipe r WHERE active=true AND (" +
                    "LOWER(name) LIKE LOWER(:query) OR " +
                    "LOWER(description) LIKE LOWER(:query))");
            sqlQuery.setParameter("query", "%" + query + "%");
            results.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }

        return results;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Recipe> getRecipesByCategory(int id) {
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        List<Recipe> result = new ArrayList<Recipe>();
        EntityManager em = emf.createEntityManager();
        try {
            Query sqlQuery = em.createQuery("SELECT r FROM Recipe r WHERE category=:category AND active=true");
            sqlQuery.setParameter("category", categoryDao.getCategory(id));
            result.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<Recipe> getRecipePage(int page, int pageSize) {
        List<Recipe> result = new ArrayList<Recipe>();
        EntityManager em = emf.createEntityManager();
        try {
            Query sqlQuery = em.createQuery("SELECT r FROM Recipe r WHERE active=true");
            sqlQuery.setFirstResult(page*pageSize - pageSize);
            sqlQuery.setMaxResults(pageSize);
            result.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }
        return result;
    }
}

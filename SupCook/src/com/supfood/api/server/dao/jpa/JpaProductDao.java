package com.supfood.api.server.dao.jpa;

import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.ProductDao;
import com.supfood.api.server.dao.RecipeDao;
import com.supfood.entity.Product;
import com.supfood.entity.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JpaProductDao implements ProductDao {

    private final EntityManagerFactory emf;


    public JpaProductDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addProduct(Product product) {
        JpaObjectDao.addObject(emf, product, product.getId(), Product.class);
    }

    @Override
    public Product getProduct(int id) {
        return (Product) JpaObjectDao.getObject(emf, id, Product.class);
    }

    @Override
    public void updateProduct(Product product) {
        JpaObjectDao.updateObject(emf, product, product.getId(), Product.class);
    }

    @Override
    public void removeProductById(int id) {
        JpaObjectDao.removeObjectById(emf, id, Product.class);
    }

    @Override
    public void removeProduct(Product product) {
        removeProductById(product.getId());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getRecipeProducts(Recipe recipe) {
        List<Product> result = new ArrayList<Product>();
        EntityManager em = emf.createEntityManager();
        try {
            Query sqlQuery = em.createQuery("SELECT p FROM Product p WHERE recipe=:recipe AND active=true");
            sqlQuery.setParameter("recipe", recipe);
            result.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<Product> getRecipeProducts(int recipeId) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        Recipe recipe = recipeDao.getRecipe(recipeId);
        return getRecipeProducts(recipe);
    }
}

package com.supinfo.supcommerce.dao.jpa;

import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaProductDao extends JpaObjectDao implements ProductDao {
    public JpaProductDao(EntityManagerFactory emf) {
        this.emf = emf;
        this.clazz = Product.class;
    }

    @Override
    public void addProduct(Product product) {
        this.addObject(product);
    }

    @Override
    public void removeProduct(Product product) {
        this.removeObject(product);
    }

    @Override
    public void removeProductById(long id) {
        this.removeObjectById(id);
    }

    @Override
    public Product findProductById(long id) {
        return (Product) this.findObjectById(id);
    }

    @Override
    public List<Product> getAll() {
        return getProducts("");
    }

    @Override
    public List<Product> getCheaperProducts(float maxPrice) {
        return getProducts("WHERE p.price < 100");
    }

    private List<Product> getProducts(String suffix) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product AS p " + suffix).getResultList();
        } finally {
            em.close();
        }
    }
}

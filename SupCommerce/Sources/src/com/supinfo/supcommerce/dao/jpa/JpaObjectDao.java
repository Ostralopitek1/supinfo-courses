package com.supinfo.supcommerce.dao.jpa;

import com.supinfo.supcommerce.exception.UnknownItemException;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public abstract class JpaObjectDao {
    protected EntityManagerFactory emf;
    protected Class clazz;

    public JpaObjectDao() {
    }

    protected void addObject(Object object) throws EntityExistsException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(object);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    protected void removeObject(Object object) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.remove(object);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    protected void removeObjectById(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            System.out.println(clazz.getTypeName()+ "type");
            System.out.println(clazz.getCanonicalName()+ "canon");
            System.out.println(clazz.getSimpleName()+ "simple");
            System.out.println(clazz.getName()+ "name");
            Query query = em.createQuery("DELETE FROM " + clazz.getName() + " AS o WHERE o.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    protected Object findObjectById(long objId) {
        EntityManager em = emf.createEntityManager();
        Object object;
        try {
            em.getTransaction().begin();
            object = em.find(clazz, objId);
            if (object == null) {
                throw new UnknownItemException(clazz, objId);
            }
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        return object;
    }

    public List<Object> getAllAsObjects() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT o FROM " + clazz.getName() + " AS o").getResultList();
        } finally {
            em.close();
        }
    }
}

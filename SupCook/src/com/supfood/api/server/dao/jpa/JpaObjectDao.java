package com.supfood.api.server.dao.jpa;

import com.supfood.api.server.exception.ItemAlreadyExistException;
import com.supfood.api.server.exception.UnknownItemException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

class JpaObjectDao {

    static void addObject(EntityManagerFactory emf, Object object, int objId, Class objClass) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            if (em.find(objClass, objId) != null) {
                throw new ItemAlreadyExistException(object);
            }
            em.persist(object);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    static void updateObject(EntityManagerFactory emf, Object object, int objId, Class objClass) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            throw new UnknownItemException(objClass, objId);
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    static void removeObjectById(EntityManagerFactory emf, int objId, Class objClass) {
        Object object = getObject(emf, objId, objClass);
        removeObject(emf, object);
    }

    static void removeObject(EntityManagerFactory emf, Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    static Object getObject(EntityManagerFactory emf, int objId, Class objClass) {
        EntityManager em = emf.createEntityManager();
        Object object;
        try {
            em.getTransaction().begin();
            object = em.find(objClass, objId);
            if (object == null) {
                throw new UnknownItemException(objClass, objId);
            }
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        return object;
    }
}

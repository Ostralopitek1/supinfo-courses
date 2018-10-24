package com.supfood.api.server.dao.jpa;

import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.MarkDao;
import com.supfood.api.server.dao.RecipeDao;
import com.supfood.api.server.dao.UserDao;
import com.supfood.entity.Mark;
import com.supfood.entity.Recipe;
import com.supfood.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JpaMarkDao implements MarkDao {

    private final EntityManagerFactory emf;


    public JpaMarkDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addMark(Mark mark) {
        JpaObjectDao.addObject(emf, mark, mark.getId(), Mark.class);
    }

    @Override
    public void updateMark(Mark mark) {
        JpaObjectDao.updateObject(emf, mark, mark.getId(), Mark.class);
    }

    @Override
    public void removeMarkById(int id) {
        JpaObjectDao.removeObjectById(emf, id, Mark.class);
    }

    @Override
    public void removeMark(Mark mark) {
        removeMarkById(mark.getId());
    }

    @Override
    public Mark getMark(int id) {
        Object object = JpaObjectDao.getObject(emf, id, Mark.class);
        if (object == null) {
            return null;
        }
        return (Mark) object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Mark> getUserMarks(User user) {
        List<Mark> result = new ArrayList<Mark>();
        EntityManager em = emf.createEntityManager();
        try {
            Query sqlQuery = em.createQuery("SELECT m FROM Mark m WHERE user=:user");
            sqlQuery.setParameter("user", user);
            result.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<Mark> getUserMarks(int userId) {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user = userDao.getUser(userId);
        return getUserMarks(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Mark> getRecipeMarks(Recipe recipe) {
        List<Mark> result = new ArrayList<Mark>();
        EntityManager em = emf.createEntityManager();
        try {
            Query sqlQuery = em.createQuery("SELECT m FROM Mark m WHERE recipe=:recipe");
            sqlQuery.setParameter("recipe", recipe);
            result.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<Mark> getRecipeMarks(int recipeId) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        Recipe recipe = recipeDao.getRecipe(recipeId);
        return getRecipeMarks(recipe);
    }
}

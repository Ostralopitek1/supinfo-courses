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
import java.util.ArrayList;
import java.util.List;

public class JpaUserDao implements UserDao {

    private final EntityManagerFactory emf;


    public JpaUserDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addUser(User user) {
        JpaObjectDao.addObject(emf, user, user.getId(), User.class);
    }

    @Override
    public void updateUser(User user) {
        JpaObjectDao.updateObject(emf, user, user.getId(), User.class);
    }

    @Override
    public void removeUserById(int id) {
        User user = (User) JpaObjectDao.getObject(emf, id, User.class);
        removeUser(user);
    }

    @Override
    public void removeUser(User user) {
        removeChildrens(user);
        JpaObjectDao.removeObject(emf, user);
    }

    private void removeChildrens(User user) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        List<Recipe> recipes = recipeDao.getUserRecipes(user);
        for (Recipe recipe : recipes) {
            recipeDao.removeRecipe(recipe);
        }

        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        List<Mark> marks = markDao.getUserMarks(user);
        for (Mark mark : marks) {
            mark.setUser(null);
            markDao.updateMark(mark);
        }
    }

    @Override
    public User getUser(int id) {
        Object object = JpaObjectDao.getObject(emf, id, User.class);
        if (object == null) {
            return null;
        }
        return (User) object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<User>();
        EntityManager em = emf.createEntityManager();
        try {
            result.addAll(em.createQuery("SELECT u FROM User u").getResultList());
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public User authenticate(String identifier, String password) {
        EntityManager em = emf.createEntityManager();
        Object object;
        String encodedPassword = User.encodePassword(password);
        try {
            em.getTransaction().begin();
            object = em.createQuery("SELECT u FROM User u WHERE " +
                        "(email=:email OR " +
                        "username=:username) AND " +
                        "password=:password")
                    .setParameter("email", identifier)
                    .setParameter("username", identifier)
                    .setParameter("password", encodedPassword)
                    .getSingleResult();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        return (User) object;
    }
}

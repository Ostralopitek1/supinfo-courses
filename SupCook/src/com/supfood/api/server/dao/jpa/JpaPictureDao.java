package com.supfood.api.server.dao.jpa;

import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.PictureDao;
import com.supfood.api.server.dao.RecipeDao;
import com.supfood.entity.Picture;
import com.supfood.entity.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JpaPictureDao implements PictureDao {

    private final EntityManagerFactory emf;


    public JpaPictureDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addPicture(Picture picture) {
        JpaObjectDao.addObject(emf, picture, picture.getId(), Picture.class);
    }

    @Override
    public void updatePicture(Picture picture) {
        JpaObjectDao.updateObject(emf, picture, picture.getId(), Picture.class);
    }

    @Override
    public void removePictureById(int id) {
        JpaObjectDao.removeObjectById(emf, id, Picture.class);
    }

    @Override
    public void removePicture(Picture picture) {
        removePictureById(picture.getId());
    }

    @Override
    public Picture getPicture(int pictureId) {
        return (Picture) JpaObjectDao.getObject(emf, pictureId, Picture.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Picture> getRecipePictures(Recipe recipe) {
        List<Picture> result = new ArrayList<Picture>();
        EntityManager em = emf.createEntityManager();
        try {
            Query sqlQuery = em.createQuery("SELECT p FROM Picture p WHERE recipe=:recipe AND active=true");
            sqlQuery.setParameter("recipe", recipe);
            result.addAll(sqlQuery.getResultList());
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<Picture> getRecipePictures(int recipeId) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        Recipe recipe = recipeDao.getRecipe(recipeId);
        return getRecipePictures(recipe);
    }
}

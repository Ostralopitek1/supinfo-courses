package com.supfood.api.server.dao;

import com.supfood.entity.Picture;
import com.supfood.entity.Recipe;

import java.util.List;

public interface PictureDao {

    void addPicture(Picture picture);

    void updatePicture(Picture picture);

    List<Picture> getRecipePictures(Recipe recipe);

    List<Picture> getRecipePictures(int recipeId);

    void removePictureById(int id);

    void removePicture(Picture picture);

    Picture getPicture(int pictureId);
}

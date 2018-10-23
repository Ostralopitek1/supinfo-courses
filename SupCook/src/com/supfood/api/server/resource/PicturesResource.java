package com.supfood.api.server.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.gson.GsonUtils;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.PictureDao;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Picture;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.List;

@Path("/pictures")
public class PicturesResource {

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createPicture(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Picture picture = gson.fromJson(payload, Picture.class);
        PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
        try {
            pictureDao.addPicture(picture);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }

    @GET
    @Path("/{id}")
    public String getPicture(@PathParam("id") int pictureId) {
        PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
        Picture picture;
        try {
            picture = pictureDao.getPicture(pictureId);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        String json = gson.toJson(picture);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/recipe/{recipeId}")
    public String getRecipePictures(@PathParam("recipeId") int recipeId) {
        PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
        List<Picture> pictures = pictureDao.getRecipePictures(recipeId);
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Picture>>(){}.getType();
        String json = gson.toJson(pictures, type);
        return ApiResponse.getAsJson(true, json);
    }

    @PUT
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updatePicture(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Picture picture = gson.fromJson(payload, Picture.class);
        PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
        pictureDao.updatePicture(picture);
        return ApiResponse.getAsJson(true, "success");
    }

    @DELETE
    @Path("/auth/{id}")
    public String removePicture(@PathParam("id") int id) {
        PictureDao pictureDao = DaoFactory.getInstance().getPictureDao();
        pictureDao.removePictureById(id);
        return ApiResponse.getAsJson(true, "success");
    }
}

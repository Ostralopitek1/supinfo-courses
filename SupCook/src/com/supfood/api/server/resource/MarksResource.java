package com.supfood.api.server.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.gson.GsonUtils;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.MarkDao;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Mark;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.List;

@Path("/marks")
public class MarksResource {

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createMark(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Mark mark = gson.fromJson(payload, Mark.class);
        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        try {
            markDao.addMark(mark);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }

    @GET
    @Path("/{id}")
    public String getMark(@PathParam("id") int id) {
        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        Mark mark;
        try {
            mark = markDao.getMark(id);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        String json = gson.toJson(mark);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/recipe/{recipeId}")
    public String getRecipeMarks(@PathParam("recipeId") int recipeId) {
        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        List<Mark> marks = markDao.getRecipeMarks(recipeId);
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Mark>>(){}.getType();
        String json = gson.toJson(marks, type);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/auth/user/{userId}")
    public String getUserMarks(@PathParam("userId") int userId) {
        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        List<Mark> marks = markDao.getUserMarks(userId);
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Mark>>(){}.getType();
        String json = gson.toJson(marks, type);
        return ApiResponse.getAsJson(true, json);
    }

    @PUT
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateMark(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Mark mark = gson.fromJson(payload, Mark.class);
        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        markDao.updateMark(mark);
        return ApiResponse.getAsJson(true, "success");
    }

    @DELETE
    @Path("/auth/{id}")
    public String removeMark(@PathParam("id") int id) {
        MarkDao markDao = DaoFactory.getInstance().getMarkDao();
        markDao.removeMarkById(id);
        return ApiResponse.getAsJson(true, "success");
    }
}

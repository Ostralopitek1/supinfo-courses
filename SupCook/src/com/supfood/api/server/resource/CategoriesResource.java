package com.supfood.api.server.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.gson.GsonUtils;
import com.supfood.api.server.dao.CategoryDao;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.List;

@Path("/categories")
public class CategoriesResource {

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createCategory(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Category category = gson.fromJson(payload, Category.class);
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        try {
            categoryDao.addCategory(category);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }


    @GET
    public String getAllCategories() {
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        List<Category> categories;
        try {
            categories = categoryDao.getAllCategories();
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Category>>(){}.getType();
        String json = gson.toJson(categories, type);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/{id}")
    public String getCategory(@PathParam("id") int id) {
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        Category category = categoryDao.getCategory(id);
        Gson gson = GsonUtils.getGsonBuilder();
        String json = gson.toJson(category);
        return ApiResponse.getAsJson(true, json);
    }

    @PUT
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateCategory(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Category category = gson.fromJson(payload, Category.class);
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        categoryDao.updateCategory(category);
        return ApiResponse.getAsJson(true, "success");
    }

    @DELETE
    @Path("/auth/{id}")
    public String removeCategory(@PathParam("id") int id) {
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        categoryDao.removeCategoryById(id);
        return ApiResponse.getAsJson(true, "success");
    }
}

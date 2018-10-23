package com.supfood.api.server.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.gson.GsonUtils;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.UserDao;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.List;

@Path("/users")
public class UsersResource {

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        User user = gson.fromJson(payload, User.class);
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }

    @GET
    public String getAllUsers() {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users;
        try {
            users = userDao.getAllUsers();
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<User>>(){}.getType();
        String json = gson.toJson(users, type);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/{id}")
    public String getUser(@PathParam("id") int id) {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user;
        try {
            user = userDao.getUser(id);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        String json = gson.toJson(user);
        return ApiResponse.getAsJson(true, json);
    }

    @PUT
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUser(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        User user = gson.fromJson(payload, User.class);
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }

    @DELETE
    @Path("/auth/{id}")
    public String removeUser(@PathParam("id") int id) {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.removeUserById(id);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }

    /*
        Handlers
     */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public String registerUser(String payload) {
        return this.createUser(payload);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public String authenticateUser(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        JsonObject credentials = gson.fromJson(payload, JsonObject.class);
        JsonElement identifier = credentials.get("identifier");
        JsonElement password = credentials.get("password");
        if(identifier == null || password == null) {
            return ApiResponse.getAsJson(false, "BAD_REQUEST");
        }

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            User user = userDao.authenticate(identifier.getAsString(), password.getAsString());
            String jsonUser = gson.toJson(user);
            return ApiResponse.getAsJson(true, jsonUser);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"BAD_CREDENTIALS");
        }
    }
}

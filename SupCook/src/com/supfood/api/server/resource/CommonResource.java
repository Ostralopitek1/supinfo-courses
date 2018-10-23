package com.supfood.api.server.resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.supfood.api.gson.GsonUtils;
import com.supfood.api.server.dao.CommonDao;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.entity.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class CommonResource {

    @GET
    @Path("/stats")
    public String getStats() {
        CommonDao commonDao = DaoFactory.getInstance().getCommonDao();
        // Collect stats
        int usersCount = commonDao.getUsersCount();
        int recipesCount = commonDao.getRecipesCount();
        String mostUsedProduct = commonDao.getMostUsedProductName();
        double avgMarks = commonDao.getAvgMarks();

        // Create Json stats
        JsonObject stats = new JsonObject();
        stats.addProperty("usersCount", usersCount);
        stats.addProperty("recipesCount", recipesCount);
        stats.addProperty("mostUsedProduct", mostUsedProduct);
        stats.addProperty("avgMarks", avgMarks);
        Gson gson = GsonUtils.getGsonBuilder();
        String jsonStats = gson.toJson(stats);
        return ApiResponse.getAsJson(true, jsonStats);
    }


}

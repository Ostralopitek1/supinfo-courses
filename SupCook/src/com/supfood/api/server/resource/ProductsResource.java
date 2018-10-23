package com.supfood.api.server.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.gson.GsonUtils;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.ProductDao;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.List;

@Path("/products")
public class ProductsResource {

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createProduct(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Product product = gson.fromJson(payload, Product.class);
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        productDao.addProduct(product);
        try {
            productDao.addProduct(product);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }

    @GET
    @Path("/{id}")
    public String getProduct(@PathParam("id") int productId) {
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        Product product;
        try {
            product = productDao.getProduct(productId);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        String json = gson.toJson(product);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/recipe/{recipeId}")
    public String getRecipeProducts(@PathParam("recipeId") int recipeId) {
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        List<Product> products = productDao.getRecipeProducts(recipeId);
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Product>>(){}.getType();
        String json = gson.toJson(products, type);
        return ApiResponse.getAsJson(true, json);
    }

    @PUT
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateProduct(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Product product = gson.fromJson(payload, Product.class);
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        productDao.updateProduct(product);
        return ApiResponse.getAsJson(true, "success");
    }

    @DELETE
    @Path("/auth/{id}")
    public String removeProduct(@PathParam("id") int id) {
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        productDao.removeProductById(id);
        return ApiResponse.getAsJson(true, "success");
    }
}

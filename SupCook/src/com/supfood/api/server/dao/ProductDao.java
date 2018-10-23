package com.supfood.api.server.dao;

import com.supfood.entity.Product;
import com.supfood.entity.Recipe;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);

    Product getProduct(int id);

    void updateProduct(Product product);

    void removeProductById(int id);

    List<Product> getRecipeProducts(Recipe recipe);

    List<Product> getRecipeProducts(int recipeId);

    void removeProduct(Product product);
}

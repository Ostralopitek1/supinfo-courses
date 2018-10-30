package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.entity.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);

    void removeProduct(Product product);

    Product findProductById(long id);

    List<Product> getAll();

    List<Product> getCheaperProducts(float maxPrice);

    void removeProductById(long id);
}

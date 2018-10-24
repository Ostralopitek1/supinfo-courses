package com.supfood.api.server.dao;

import com.supfood.entity.Category;

import java.util.List;

public interface CategoryDao {

    void addCategory(Category category);

    void updateCategory(Category category);

    void removeCategoryById(int id);

    void removeCategory(Category category);

    Category getCategory(int id);

    List<Category> getAllCategories();
}

package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.entity.Category;

import java.util.List;

public interface CategoryDao {
    public void addCategory(Category category);

    public void removeCategory(Category category);

    Category findCategoryById(long id);

    List<Category> getAll();
}

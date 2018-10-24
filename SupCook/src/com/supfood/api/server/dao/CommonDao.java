package com.supfood.api.server.dao;

public interface CommonDao {

    int getUsersCount();

    int getRecipesCount();

    String getMostUsedProductName();

    double getAvgMarks();
}

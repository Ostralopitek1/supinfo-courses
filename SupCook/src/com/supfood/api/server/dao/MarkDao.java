package com.supfood.api.server.dao;

import com.supfood.entity.Mark;
import com.supfood.entity.Recipe;
import com.supfood.entity.User;

import java.util.List;

public interface MarkDao {

    void addMark(Mark mark);

    void updateMark(Mark mark);

    void removeMarkById(int id);

    void removeMark(Mark mark);

    Mark getMark(int id);

    List<Mark> getUserMarks(User user);

    List<Mark> getUserMarks(int recipeId);

    List<Mark> getRecipeMarks(Recipe recipe);

    List<Mark> getRecipeMarks(int recipeId);
}
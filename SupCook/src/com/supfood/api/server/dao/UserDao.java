package com.supfood.api.server.dao;

import com.supfood.entity.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void updateUser(User user);

    void removeUserById(int id);

    void removeUser(User user);

    User getUser(int id);

    List<User> getAllUsers();

    User authenticate(String identifier, String password);
}

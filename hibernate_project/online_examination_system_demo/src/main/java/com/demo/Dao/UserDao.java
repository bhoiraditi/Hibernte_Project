package com.demo.Dao;

import java.util.List;

import com.demo.examinationsystem.entities.User;

public interface UserDao {

    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(int userId);
    User updateUser(int userId, User updatedUser);
    String deleteUser(int userId);
}

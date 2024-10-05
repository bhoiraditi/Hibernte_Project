package com.demo.ServiceImpl;

import java.util.List;

import com.demo.Dao.UserDao;
import com.demo.DaoImpl.UserDaoImpl;
import com.demo.examinationsystem.entities.User;
import com.demo.Service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public User createUser(User user) {
        // Invoke dao method to save user object
        return userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        // Retrieve all users
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(int userId) {
        // Retrieve user by ID
        return userDao.getUserById(userId);
    }

    @Override
    public User updateUser(int userId, User updatedUser) {
        // Update user details
        return userDao.updateUser(userId, updatedUser);
    }

    @Override
    public String deleteUser(int userId) {
        // Delete user by ID
        return userDao.deleteUser(userId);
    }
}

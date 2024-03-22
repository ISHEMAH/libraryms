package com.example.jspapp.dao;

import com.example.jspapp.classes.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    User loginUser(String username, String password);
}

package com.example.demo.service;

import com.example.demo.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void addUser(User user);

    User findUserById(Long id);

    void deleteUserById(Long id);

    User findUserByName(String name);

}

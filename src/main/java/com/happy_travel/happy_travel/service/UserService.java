package com.happy_travel.happy_travel.service;

import java.util.List;

import com.happy_travel.happy_travel.entity.User;

public interface UserService {
    User getUserById(Long id);
    List<User> getUsers();
    User saveUser(User user);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
}

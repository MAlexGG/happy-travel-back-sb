package com.happy_travel.happy_travel.service;

import java.util.List;

import com.happy_travel.happy_travel.dto.request.UserUpdateRequest;
import com.happy_travel.happy_travel.dto.response.user.UserResponse;
import com.happy_travel.happy_travel.entity.User;

public interface UserService {
    User getUserById(Long id);
    List<UserResponse> getUsers();
    User saveUser(User user);
    User updateUser(UserUpdateRequest request);
    String deleteUser(Long id);
    User getUser(String name);
    User getAuthenticatedUser();

}

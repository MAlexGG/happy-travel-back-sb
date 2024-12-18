package com.happy_travel.happy_travel.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.happy_travel.happy_travel.dto.request.UserUpdateRequest;
import com.happy_travel.happy_travel.dto.response.user.UserResponse;
import com.happy_travel.happy_travel.entity.User;
import com.happy_travel.happy_travel.exception.EmptyException;
import com.happy_travel.happy_travel.exception.EntityNotFoundException;
import com.happy_travel.happy_travel.repository.UserRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRespository userRespository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRespository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRespository.findAll();
        if (users.isEmpty()) throw new EmptyException();
        return users.stream()
            .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail()))
            .collect(Collectors.toList());
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRespository.save(user);
    }

    @Override
    public User updateUser(UserUpdateRequest updatedUser) {
        User user = getAuthenticatedUser();
        user.setEmail(updatedUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        return userRespository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        getUserById(id);
        userRespository.deleteById(id);
        return "Usuario con id " + id + " eliminado con éxito"; 
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRespository.findByUsername(username);
        return unwrapUser(user, null);
    }

    static User unwrapUser(Optional<User> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }

    @Override
    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        Optional<User> user = userRespository.findByUsername(username);
        User unwrappedUser = UserServiceImpl.unwrapUser(user, (user.get()).getId());
       return unwrappedUser;
    }
}
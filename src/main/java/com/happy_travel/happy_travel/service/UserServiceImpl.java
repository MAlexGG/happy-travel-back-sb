package com.happy_travel.happy_travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public List<User> getUsers() {
        List<User> users = userRespository.findAll();
        if (users.isEmpty()) throw new EmptyException();
        else return users;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRespository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> user = userRespository.findById(id);
        User unwrappedUser = unwrapUser(user, id); 
        unwrappedUser.setName(updatedUser.getName());
        unwrappedUser.setEmail(updatedUser.getEmail());
        unwrappedUser.setPassword(updatedUser.getPassword());
        return userRespository.save(unwrappedUser);
    }

    @Override
    public String deleteUser(Long id) {
        getUserById(id);
        userRespository.deleteById(id);
        return "Usuario con id " + id + " eliminado con éxito"; 
    }

    @Override
    public User getUser(String name) {
        Optional<User> user = userRespository.findByName(name);
        return unwrapUser(user, null);
    }

    static User unwrapUser(Optional<User> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
}
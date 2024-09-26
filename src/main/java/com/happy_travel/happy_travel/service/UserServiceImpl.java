package com.happy_travel.happy_travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.happy_travel.happy_travel.entity.User;
import com.happy_travel.happy_travel.exception.EntityNotFoundException;
import com.happy_travel.happy_travel.repository.UserRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRespository userRespository;

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRespository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    static User unwrapUser(Optional<User> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }


}
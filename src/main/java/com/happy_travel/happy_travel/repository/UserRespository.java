package com.happy_travel.happy_travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happy_travel.happy_travel.entity.User;

public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
} 
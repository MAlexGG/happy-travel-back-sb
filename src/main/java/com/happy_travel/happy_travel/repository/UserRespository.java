package com.happy_travel.happy_travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happy_travel.happy_travel.entity.User;

public interface UserRespository extends JpaRepository<User, Long> {} 
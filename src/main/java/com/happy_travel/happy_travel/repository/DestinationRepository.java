package com.happy_travel.happy_travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.entity.User;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByUser(User user);
    List<Destination> findByName(String name);
    List<Destination> findByDescriptionContaining(String destination);
}

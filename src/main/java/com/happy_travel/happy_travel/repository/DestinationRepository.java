package com.happy_travel.happy_travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happy_travel.happy_travel.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {}

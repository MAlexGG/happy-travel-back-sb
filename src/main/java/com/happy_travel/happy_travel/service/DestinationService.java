package com.happy_travel.happy_travel.service;

import java.util.List;


import com.happy_travel.happy_travel.entity.Destination;

public interface DestinationService {
    Destination getDestinationById(Long id);
    List<Destination> getDestinations();
    Destination saveDestination(Destination destination);
    Destination updateDestination(Long id, Destination updatedDestination);
    void deleteDestination(Long id);
    List<Destination> getUserDestinations(Long userId);
}
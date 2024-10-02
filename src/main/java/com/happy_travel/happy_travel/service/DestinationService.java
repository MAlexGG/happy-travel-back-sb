package com.happy_travel.happy_travel.service;

import java.util.List;

import com.happy_travel.happy_travel.dto.response.destination.DestinationResponse;
import com.happy_travel.happy_travel.entity.Destination;

public interface DestinationService {
    Destination getDestinationById(Long id);
    List<DestinationResponse> getDestinations();
    Destination saveDestination(Destination destination);
    Destination updateDestination(Long id, Destination updatedDestination);
    String deleteDestination(Long id);
    List<Destination> getUserDestinations(Long userId);
    List<Destination> searchDestinationsByName(String name);
    List<Destination> searchDestinationsByDescription(String description);
}

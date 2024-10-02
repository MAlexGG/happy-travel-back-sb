package com.happy_travel.happy_travel.service;

import java.util.List;

import com.happy_travel.happy_travel.dto.response.destination.DestinationByUserResponse;
import com.happy_travel.happy_travel.dto.response.destination.DestinationResponse;
import com.happy_travel.happy_travel.entity.Destination;

public interface DestinationService {
    DestinationResponse getDestinationById(Long id);
    List<DestinationResponse> getDestinations();
    Destination saveDestination(Destination destination);
    Destination updateDestination(Long id, Destination updatedDestination);
    String deleteDestination(Long id);
    DestinationByUserResponse getUserDestinations(Long userId);
    List<DestinationResponse> searchDestinationsByName(String name);
    List<DestinationResponse> searchDestinationsByDescription(String description);
}

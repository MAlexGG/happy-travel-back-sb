package com.happy_travel.happy_travel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.repository.DestinationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DestinationServiceImpl implements DestinationService{

    DestinationRepository destinationRepository;

    @Override
    public Destination getDestinationById(Long id) {
        return null;
    }

    @Override
    public List<Destination> getDestinations() {
        return null;
    }

    @Override
    public Destination saveDestination(Destination destination) {
        return null;
    }

    @Override
    public Destination updateDestination(Long id, Destination updatedDestination) {
        return null;
    }

    @Override
    public void deleteDestination(Long id) {
        
    }

    @Override
    public List<Destination> getUserDestinations(Long userId) {
        return null;
    }
    
}

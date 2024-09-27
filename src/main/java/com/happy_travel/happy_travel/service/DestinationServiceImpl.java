package com.happy_travel.happy_travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.entity.User;
import com.happy_travel.happy_travel.exception.EntityNotFoundException;
import com.happy_travel.happy_travel.repository.DestinationRepository;
import com.happy_travel.happy_travel.repository.UserRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DestinationServiceImpl implements DestinationService{

    DestinationRepository destinationRepository;
    UserRespository userRespository;

    @Override
    public Destination getDestinationById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        return unwrapDestination(destination, id);
    }

    @Override
    public List<Destination> getDestinations() {
        return null;
    }

    @Override
    public Destination saveDestination(Destination destination, Long userId) {
        Optional<User> user = userRespository.findById(userId);
        User unwrappedUser = UserServiceImpl.unwrapUser(user, userId);
        destination.setUser(unwrappedUser);
        return destinationRepository.save(destination);
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

    static Destination unwrapDestination(Optional<Destination> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Destination.class);
    }
    
}

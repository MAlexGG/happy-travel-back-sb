package com.happy_travel.happy_travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.entity.User;
import com.happy_travel.happy_travel.exception.EmptyException;
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
        List<Destination> destinations = destinationRepository.findAll();
        if(destinations.isEmpty()) throw new EmptyException();
        return destinations;
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
        Optional<Destination> destination = destinationRepository.findById(id);
        Destination unwrappedDestination = unwrapDestination(destination, id);
        unwrappedDestination.setName(updatedDestination.getName());
        unwrappedDestination.setDescription(updatedDestination.getDescription());
        unwrappedDestination.setImage(updatedDestination.getImage());
        return destinationRepository.save(unwrappedDestination);
    }

    @Override
    public String deleteDestination(Long id) {
        getDestinationById(id);
        destinationRepository.deleteById(id);
        return "Destino con id " + id + " eliminado con éxito";
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

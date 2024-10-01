package com.happy_travel.happy_travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.entity.User;
import com.happy_travel.happy_travel.exception.CustomAccessDeniedException;
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
    public Destination saveDestination(Destination destination) {
        User user = getAuthenticatedUser();
        destination.setUser(user);
        return destinationRepository.save(destination);
    }

    @Override
    public Destination updateDestination(Long id, Destination updatedDestination) {
        Long userId = getAuthenticatedUser().getId();
        Destination destination = destinationRepository.findByIdAndUserId(id, userId);
        if(destination == null) throw new CustomAccessDeniedException();
        destination.setName(updatedDestination.getName());
        destination.setDescription(updatedDestination.getDescription());
        destination.setImage(updatedDestination.getImage());
        return destinationRepository.save(destination);
    }
    
    @Override
    public String deleteDestination(Long id) {
        Long userId = getAuthenticatedUser().getId();
        Destination destination = destinationRepository.findByIdAndUserId(id, userId);
        if(destination == null) throw new CustomAccessDeniedException();
        destinationRepository.delete(destination);
        return "Destino con id " + id + " eliminado con éxito";
    }

    @Override
    public List<Destination> getUserDestinations(Long userId) {
        Optional<User> user = userRespository.findById(userId);
        User unwrappedUser = UserServiceImpl.unwrapUser(user, userId);
        return destinationRepository.findByUser(unwrappedUser);
    }

    @Override
    public List<Destination> searchDestinationsByName(String name) {
        List<Destination> destinations = destinationRepository.findByName(name);
        if(destinations.isEmpty()) throw new EmptyException();
        return destinations;
    }

    @Override
    public List<Destination> searchDestinationsByDescription(String description) {
        List<Destination> destinations = destinationRepository.findByDescriptionContaining(description);
        if(destinations.isEmpty()) throw new EmptyException();
        return destinations;
    }

    static Destination unwrapDestination(Optional<Destination> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Destination.class);
    }

    private User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        Optional<User> user = userRespository.findByUsername(username);
        User unwrappedUser = UserServiceImpl.unwrapUser(user, (user.get()).getId());
       return unwrappedUser;
    }
    
}

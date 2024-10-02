package com.happy_travel.happy_travel.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.happy_travel.happy_travel.dto.response.destination.DestinationByUserResponse;
import com.happy_travel.happy_travel.dto.response.destination.DestinationResponse;
import com.happy_travel.happy_travel.dto.response.user.UserResponse;
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
    UserService userService;

    @Override
    public DestinationResponse getDestinationById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        Destination unwrappedDestination = unwrapDestination(destination, id);
        return new DestinationResponse(unwrappedDestination.getId(), unwrappedDestination.getName(), unwrappedDestination.getDescription(), unwrappedDestination.getImage(), new UserResponse(
            unwrappedDestination.getUser().getId(),
            unwrappedDestination.getUser().getUsername(),
            unwrappedDestination.getUser().getEmail()
        ));
    }

    @Override
    public List<DestinationResponse> getDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        if(destinations.isEmpty()) throw new EmptyException();
        return destinations.stream()
            .map(destination -> new DestinationResponse(destination.getId(), destination.getName(), destination.getDescription(), destination.getImage(), new UserResponse(
                destination.getUser().getId(),
                destination.getUser().getUsername(),
                destination.getUser().getEmail()
            )))
            .collect(Collectors.toList());
    }

    @Override
    public Destination saveDestination(Destination destination) {
        User user = userService.getAuthenticatedUser();
        destination.setUser(user);
        return destinationRepository.save(destination);
    }

    @Override
    public Destination updateDestination(Long id, Destination updatedDestination) {
        Long userId = userService.getAuthenticatedUser().getId();
        Destination destination = destinationRepository.findByIdAndUserId(id, userId);
        if(destination == null) throw new CustomAccessDeniedException();
        destination.setName(updatedDestination.getName());
        destination.setDescription(updatedDestination.getDescription());
        destination.setImage(updatedDestination.getImage());
        return destinationRepository.save(destination);
    }
    
    @Override
    public String deleteDestination(Long id) {
        Long userId = userService.getAuthenticatedUser().getId();
        Destination destination = destinationRepository.findByIdAndUserId(id, userId);
        if(destination == null) throw new CustomAccessDeniedException();
        destinationRepository.delete(destination);
        return "Destino con id " + id + " eliminado con éxito";
    }

    @Override
    public DestinationByUserResponse getUserDestinations(Long userId) {
        Optional<User> user = userRespository.findById(userId);
        User unwrappedUser = UserServiceImpl.unwrapUser(user, userId);
        UserResponse userResponse = new UserResponse(unwrappedUser.getId(), unwrappedUser.getUsername(), unwrappedUser.getEmail());

        List<Destination> destinations = destinationRepository.findByUser(unwrappedUser);
        List<DestinationResponse> destinationsResponse = destinations.stream().map(destination -> new DestinationResponse(
            destination.getId(), destination.getName(), destination.getDescription(), destination.getImage(), null
        )).collect(Collectors.toList());
        return new DestinationByUserResponse(userResponse, destinationsResponse);
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
}

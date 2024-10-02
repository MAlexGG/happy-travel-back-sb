package com.happy_travel.happy_travel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy_travel.happy_travel.dto.response.destination.DestinationByUserResponse;
import com.happy_travel.happy_travel.dto.response.destination.DestinationResponse;
import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.service.DestinationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/destination")
@AllArgsConstructor
public class DestinationController {
    
    DestinationService destinationService;

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse> getDestinationById(@PathVariable Long id) {
        return new ResponseEntity<>(destinationService.getDestinationById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DestinationResponse>> getDestinations() {
        return new ResponseEntity<>(destinationService.getDestinations(), HttpStatus.OK);
    }
  
    @PostMapping
    public ResponseEntity<Destination> saveDestination(@Valid @RequestBody Destination destination) {
        return new ResponseEntity<>(destinationService.saveDestination(destination),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable Long id, @Valid @RequestBody Destination updatedDestination) {
        return new ResponseEntity<>(destinationService.updateDestination(id, updatedDestination), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long id){
        return new ResponseEntity<>(destinationService.deleteDestination(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<DestinationByUserResponse> getUserDestinations(@PathVariable Long id) {
        return new ResponseEntity<>(destinationService.getUserDestinations(id), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Destination>> searchByName(@RequestParam String name) {
        return new ResponseEntity<>(destinationService.searchDestinationsByName(name), HttpStatus.OK);
    }

    @GetMapping("/search-like")
    public ResponseEntity<List<Destination>> searchByDescription(@RequestParam String description) {
        return new ResponseEntity<>(destinationService.searchDestinationsByDescription(description), HttpStatus.OK);
    }

    
    
    
}


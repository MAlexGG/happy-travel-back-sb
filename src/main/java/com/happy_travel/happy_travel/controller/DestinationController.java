package com.happy_travel.happy_travel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Destination> getDestinationById(@PathVariable Long id) {
        return new ResponseEntity<>(destinationService.getDestinationById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Destination>> getDestinations() {
        return new ResponseEntity<List<Destination>>(destinationService.getDestinations(), HttpStatus.OK);
    }
    
    @PostMapping("/user/{id}")
    public ResponseEntity<Destination> saveDestination(@Valid @RequestBody Destination destination, @PathVariable Long id) {
        return new ResponseEntity<>(destinationService.saveDestination(destination, id),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable Long id, @Valid @RequestBody Destination updatedDestination) {
        return new ResponseEntity<Destination>(destinationService.updateDestination(id, updatedDestination), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long id){
        return new ResponseEntity<String>(destinationService.deleteDestination(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Destination>> getUserDestinations(@PathVariable Long id) {
        return new ResponseEntity<List<Destination>>(destinationService.getUserDestinations(id), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Destination>> getMethodName(@RequestParam String name) {
        return new ResponseEntity<List<Destination>>(destinationService.searchDestinationsByName(name), HttpStatus.OK);
    }
    
    
}


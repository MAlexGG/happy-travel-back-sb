package com.happy_travel.happy_travel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.service.DestinationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/destination")
@AllArgsConstructor
public class DestinationController {
    
    DestinationService destinationService;

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Long id) {
        return new ResponseEntity<>(destinationService.getDestinationById(id), HttpStatus.OK);
    }
    
    @PostMapping("/user/{id}")
    public ResponseEntity<Destination> saveDestination(@Valid @RequestBody Destination destination, @PathVariable Long id) {
        return new ResponseEntity<>(destinationService.saveDestination(destination, id),HttpStatus.CREATED);
    }
    
}


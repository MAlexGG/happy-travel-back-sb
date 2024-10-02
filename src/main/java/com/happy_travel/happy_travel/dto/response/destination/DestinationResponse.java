package com.happy_travel.happy_travel.dto.response.destination;

import com.happy_travel.happy_travel.dto.response.user.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationResponse {
    
    private Long id;
    private String name;
    private String description;
    private String image;
    private UserResponse user;
}

package com.happy_travel.happy_travel.dto.response.destination;

import java.util.List;

import com.happy_travel.happy_travel.dto.response.user.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationByUserResponse {
    
    private UserResponse user;
    private List<DestinationResponse> destinations;

}

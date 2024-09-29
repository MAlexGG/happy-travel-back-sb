package com.happy_travel.happy_travel.config.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.happy_travel.happy_travel.entity.Destination;
import com.happy_travel.happy_travel.entity.User;
import com.happy_travel.happy_travel.repository.DestinationRepository;
import com.happy_travel.happy_travel.repository.UserRespository;
import com.happy_travel.happy_travel.service.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class Seeder {

    @Autowired
    UserRespository userRespository;

    @Autowired
    UserService userService;

    @Autowired 
    DestinationRepository destinationRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostConstruct
    public void seed(){
        seedUsers();
        seedDestinations();
    }

    public void seedUsers(){

        User[] users = new User[]{
            new User(1L, "Eli", "eli@mail.com", bCryptPasswordEncoder.encode("eli123"), null),
            new User(2L, "Gaby", "gaby@mail.com", bCryptPasswordEncoder.encode("gaby123"), null),
            new User(3L, "Alex", "alex@mail.com", bCryptPasswordEncoder.encode("alex123"), null)
        };

        for (int i = 0; i < users.length; i++) {
            userRespository.save(users[i]);
        }  
    }

    public void seedDestinations(){
        Destination[] destinations = new Destination[]{
            new Destination(1L, "USA", "Quiero ir a los casinos de las Vegas", "http://usa-lasvegas.com", userService.getUserById(1L)),
            new Destination(2L, "Aruba", "Quiero ir a las playas de Aruba nuevamente", "http://aruba.com", userService.getUserById(2L)),
            new Destination(3L, "Japón", "Quiero volver a Kamakura", "http://japon.com", userService.getUserById(3L)),
            new Destination(4L, "Brasil", "Quiero conocer Sao Paulo", "http://brasil.com", userService.getUserById(3L)),
            new Destination(5L, "México", "Quiero comer tacos y beber tequila", "http://mexico.com", userService.getUserById(3L))
        };

        for (int i = 0; i < destinations.length; i++) {
            destinationRepository.save(destinations[i]);
        }
    }

}

package com.example.productcatalogservice.commons;

import com.example.productcatalogservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {

    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        //Call userService to validateToken API to validate
        ResponseEntity<UserDto> response =
                restTemplate.postForEntity("http://localhost:8081/users/validate/" + token, null, UserDto.class);

        if (response.getBody() == null){
            // Token is InValid
            return null;
        }
        return response.getBody();
    }
}

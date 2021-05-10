package es.codeurjc.deliveryservice.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class RouteService {

    public String getRoute (String cityName){
        return Base64.getEncoder().encodeToString(cityName.getBytes());
    }
}

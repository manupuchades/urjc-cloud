package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.dto.CityResponse;
import es.codeurjc.deliveryservice.dto.CreateCityRequest;
import es.codeurjc.deliveryservice.dto.CreateCityResponse;

import java.util.UUID;

public interface ICityService {

    CreateCityResponse createCity(CreateCityRequest createCityRequest);

    CityResponse getCity(UUID cityId);
}

package es.codeurjc.deliveryservice.mappers;

import es.codeurjc.deliveryservice.domain.City;
import es.codeurjc.deliveryservice.dto.CityResponse;
import es.codeurjc.deliveryservice.dto.CreateCityRequest;

public class CityMapper {

    public static City map (CreateCityRequest createCityRequest){
        return City.builder()
                .name(createCityRequest.getName())
                .capacity(createCityRequest.getCapacity())
                .build();
    }

    public static CityResponse map (City city){
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .capacity(city.getCapacity())
                .build();
    }
}

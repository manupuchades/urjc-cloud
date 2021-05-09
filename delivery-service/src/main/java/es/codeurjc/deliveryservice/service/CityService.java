package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.domain.City;
import es.codeurjc.deliveryservice.dto.CityResponse;
import es.codeurjc.deliveryservice.dto.CreateCityRequest;
import es.codeurjc.deliveryservice.dto.CreateCityResponse;
import es.codeurjc.deliveryservice.exception.EntityNotFoundException;
import es.codeurjc.deliveryservice.mappers.CityMapper;
import es.codeurjc.deliveryservice.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CityService implements ICityService{

    CityRepository cityRepository;

    @Override
    public CreateCityResponse createCity(CreateCityRequest createCityRequest) {
        City city = cityRepository.save(CityMapper.map(createCityRequest));
        return new CreateCityResponse(city.getId());
    }

    @Override
    public CityResponse getCity(UUID cityId) {
        return CityMapper.map(cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException()));
    }
}

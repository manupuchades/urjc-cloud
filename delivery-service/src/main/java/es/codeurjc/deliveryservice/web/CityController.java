package es.codeurjc.deliveryservice.web;

import es.codeurjc.deliveryservice.dto.CityResponse;
import es.codeurjc.deliveryservice.dto.CreateCityRequest;
import es.codeurjc.deliveryservice.dto.CreateCityResponse;
import es.codeurjc.deliveryservice.service.ICityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CityController {

    private final ICityService cityService;

    @PostMapping("cities")
    public ResponseEntity<CreateCityResponse> createCity(@RequestBody CreateCityRequest createCityRequest) {
        return new ResponseEntity<>(cityService.createCity(createCityRequest), HttpStatus.CREATED);
    }

    @GetMapping("cities/{cityId}")
    public ResponseEntity<CityResponse> getCity(@PathVariable(value = "cityId") UUID cityId) {
        return new ResponseEntity<>(cityService.getCity(cityId), HttpStatus.OK);
    }
}

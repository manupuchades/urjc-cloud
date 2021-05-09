package es.codeurjc.deliveryservice.repository;

import es.codeurjc.deliveryservice.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}

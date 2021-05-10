package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.domain.City;
import es.codeurjc.deliveryservice.domain.Delivery;
import es.codeurjc.deliveryservice.dto.DeliveryResponse;
import es.codeurjc.deliveryservice.exception.EntityNotFoundException;
import es.codeurjc.deliveryservice.mappers.DeliveryMapper;
import es.codeurjc.deliveryservice.model.events.dto.OrderDto;
import es.codeurjc.deliveryservice.repository.CityRepository;
import es.codeurjc.deliveryservice.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryService implements IDeliveryService{

    private Logger log = LoggerFactory.getLogger(DeliveryService.class);

    DeliveryRepository deliveryRepository;
    CityRepository cityRepository;
    RouteService routeService;

    @Override
    public DeliveryResponse getDelivery(UUID deliveryId) {
        return DeliveryMapper.map(deliveryRepository.findById(deliveryId).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public Boolean deliverOrder(OrderDto orderDto) {
        log.debug("Delivering OrderId: " + orderDto.getId());
        Optional<City> optionalCity = cityRepository.findById(orderDto.getCityId());
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            if (city.getCapacity() > orderDto.getQuantity()) {
                city.setCapacity(city.getCapacity() - orderDto.getQuantity());
                City savedCity = cityRepository.saveAndFlush(city);
                log.debug("Updated City with city id: " + savedCity.getId());

                Delivery delivery = Delivery.builder()
                        .city(city)
                        .route(routeService.getRoute(city.getName()))
                        .build();

                Delivery savedDelivery = deliveryRepository.save(delivery);
                log.debug("Saved Delivery with delivery id: " + savedDelivery.getId());

                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public void dedeliverOrder(OrderDto orderDto) {
        Optional<City> optCity = cityRepository.findById(orderDto.getCityId());
        if (optCity.isPresent()) {
            City city = optCity.get();
            city.setCapacity(city.getCapacity() + orderDto.getQuantity());
            City savedCity = cityRepository.save(city);
            log.debug("Saved City city id: " + savedCity.getId());
        }
    }
}

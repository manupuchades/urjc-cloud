package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.domain.Delivery;
import es.codeurjc.deliveryservice.dto.DeliveryResponse;
import es.codeurjc.deliveryservice.model.events.dto.OrderDto;

import java.util.Optional;
import java.util.UUID;

public interface IDeliveryService {

    DeliveryResponse getDelivery (UUID deliveryId);

    Optional<Delivery> deliverOrder (OrderDto orderDto);

    void dedeliverOrder (OrderDto orderDto);
}

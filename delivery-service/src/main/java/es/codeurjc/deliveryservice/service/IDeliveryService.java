package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.dto.DeliveryResponse;
import es.codeurjc.deliveryservice.model.events.dto.OrderDto;

import java.util.UUID;

public interface IDeliveryService {

    DeliveryResponse getDelivery (UUID deliveryId);

    Boolean deliverOrder (OrderDto orderDto);

    void dedeliverOrder (OrderDto orderDto);
}

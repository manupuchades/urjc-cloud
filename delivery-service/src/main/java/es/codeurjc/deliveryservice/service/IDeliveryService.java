package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.dto.DeliveryResponse;

import java.util.UUID;

public interface IDeliveryService {

    DeliveryResponse getDelivery (UUID deliveryId);

}

package es.codeurjc.deliveryservice.mappers;

import es.codeurjc.deliveryservice.domain.Delivery;
import es.codeurjc.deliveryservice.dto.DeliveryResponse;

public class DeliveryMapper {

    public static DeliveryResponse map (Delivery delivery){
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .city(delivery.getCity())
                .route(delivery.getRoute())
                .state(delivery.getState())
                .createdDate(delivery.getCreatedDate())
                .lastModifiedDate(delivery.getLastModifiedDate())
                .build();
    }
}

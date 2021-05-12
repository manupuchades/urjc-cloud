package es.codeurjc.deliveryservice.dto;

import es.codeurjc.deliveryservice.domain.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {
    private UUID id;

    private String route;

    private City city;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;
}

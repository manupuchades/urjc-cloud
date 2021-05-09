package es.codeurjc.deliveryservice.dto;

import es.codeurjc.deliveryservice.domain.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import types.DeliveryStatusEnum;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {
    private Long id;

    private String route;

    private City city;

    private DeliveryStatusEnum state;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;
}

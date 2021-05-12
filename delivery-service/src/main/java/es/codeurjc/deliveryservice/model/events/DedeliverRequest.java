package es.codeurjc.deliveryservice.model.events;

import es.codeurjc.deliveryservice.model.events.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DedeliverRequest {

    private OrderDto order;

}

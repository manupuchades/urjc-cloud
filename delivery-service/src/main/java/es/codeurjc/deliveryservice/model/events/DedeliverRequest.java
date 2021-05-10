package es.codeurjc.deliveryservice.model.events;

import es.codeurjc.deliveryservice.model.events.dto.OrderDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class DedeliverRequest {

    private OrderDto order;

}

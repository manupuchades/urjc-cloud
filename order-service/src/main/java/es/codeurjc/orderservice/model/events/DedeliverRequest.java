package es.codeurjc.orderservice.model.events;

import es.codeurjc.orderservice.model.events.dto.OrderDto;
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

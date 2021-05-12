package es.codeurjc.orderservice.converter;

import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.model.events.dto.OrderDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {


    @Override
    public OrderDto convert(Order order) {
        return new OrderDto.Builder()
                .withId(order.getId())
                .withState(order.getState())
                .withName(order.getName())
                .withQuantity(order.getQuantity())
                .withReference(order.getReference())
                .withDeliveryId(order.getOrderDetails().getDeliveryId())
                .withCityId(order.getOrderDetails().getCityId())
                .build();
    }

}

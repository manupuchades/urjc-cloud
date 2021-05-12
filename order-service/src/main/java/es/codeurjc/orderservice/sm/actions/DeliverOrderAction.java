package es.codeurjc.orderservice.sm.actions;

import es.codeurjc.orderservice.converter.OrderToOrderDtoConverter;
import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.model.events.dto.OrderDto;
import es.codeurjc.orderservice.repository.OrderRepository;
import es.codeurjc.orderservice.service.OrderManager;
import es.codeurjc.orderservice.stream.kafka.OrderStreamService;
import es.codeurjc.orderservice.types.OrderEventEnum;
import es.codeurjc.orderservice.types.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Component
public class DeliverOrderAction implements Action<OrderStatusEnum, OrderEventEnum> {

    private final OrderRepository orderRepository;
    private final OrderStreamService orderStreamService;
    private final OrderToOrderDtoConverter orderToOrderDtoConverter;

    @Override
    public void execute(StateContext<OrderStatusEnum, OrderEventEnum> context) {
        String orderId = (String) context.getMessage().getHeaders().get(OrderManager.ORDER_ID_HEADER);
        Optional<Order> orderOptional = orderRepository.findById(UUID.fromString(orderId));

        orderOptional.ifPresentOrElse(order -> {

            final OrderDto orderDto = orderToOrderDtoConverter.convert(order);
            orderStreamService.sendDeliverRequest(orderDto);

        }, () -> log.error("Order Not Found. Id: " + orderId));

        log.debug("Sent Validation request to queue for order id " + orderId);
    }
}

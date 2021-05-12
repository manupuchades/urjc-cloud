package es.codeurjc.orderservice.stream.kafka;

import es.codeurjc.orderservice.common.OrderDetails;
import es.codeurjc.orderservice.domain.Order;
import es.codeurjc.orderservice.model.events.AllocateResult;
import es.codeurjc.orderservice.model.events.CreditResult;
import es.codeurjc.orderservice.model.events.DeliverResult;
import es.codeurjc.orderservice.repository.OrderRepository;
import es.codeurjc.orderservice.service.OrderManager;
import es.codeurjc.orderservice.types.RejectionReasonEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
public class OrderStreamListener {

    private Logger log = LoggerFactory.getLogger(OrderStreamListener.class);

    private final OrderRepository orderRepository;
    private final OrderManager orderManager;

    @Autowired
    public OrderStreamListener(OrderManager orderManager, OrderRepository orderRepository) {
        this.orderManager = orderManager;
        this.orderRepository = orderRepository;
    }

    @StreamListener(OrderStream.INPUT_ALLOCATE_ORDER)
    public void handleAllocateResult(@Payload AllocateResult result) {
        final UUID orderId = result.getOrderId();

        log.debug("Allocation Result for Order Id: " + orderId);

        final String reason = result.getReason();
        if (reason != null) {
            Optional<Order> orderOptional = orderRepository.findById(orderId);

            orderOptional.ifPresentOrElse(order -> {
                order.setRejectionReason(RejectionReasonEnum.valueOf(reason));
                orderRepository.save(order);
            }, () -> log.error("Order Not Found. Id: " + orderId));
        }
        orderManager.processAllocationResult(orderId, result.getIsValid());
    }

    @StreamListener(OrderStream.INPUT_CREDIT_ORDER)
    public void handleCreditResult(@Payload CreditResult result) {
        final UUID orderId = result.getOrderId();
        log.debug("Credit Result for Order Id: " + orderId);
        final String reason = result.getRejectionReason();
        if (reason != null) {
            Optional<Order> orderOptional = orderRepository.findById(orderId);

            orderOptional.ifPresentOrElse(order -> {
                order.setRejectionReason(RejectionReasonEnum.valueOf(reason));
                orderRepository.save(order);
            }, () -> log.error("Order Not Found. Id: " + orderId));
        }

        orderManager.processCreditResult(orderId, result.getIsValid(), result.getRejectionReason());
    }

    @StreamListener(OrderStream.INPUT_DELIVER_ORDER)
    public void handleDeliverResult(@Payload DeliverResult result) {
        final UUID orderId = result.getOrderId();
        log.debug("Deliver Result for Order Id: " + orderId);
        final String reason = result.getRejectionReason();

        Optional<Order> orderOptional = orderRepository.findById(orderId);

        orderOptional.ifPresentOrElse(order -> {
            order.getOrderDetails().setDeliveryId(result.getDeliveryId());
            if (reason != null) {
                order.setRejectionReason(RejectionReasonEnum.valueOf(reason));
            }
            orderRepository.save(order);
        }, () -> log.error("Order Not Found. Id: " + orderId));

        orderManager.processDeliverResult(orderId, result.getIsValid(), result.getRejectionReason());
    }
}

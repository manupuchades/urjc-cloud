package es.codeurjc.deliveryservice.stream.kafka;

import es.codeurjc.deliveryservice.model.events.DedeliverRequest;
import es.codeurjc.deliveryservice.model.events.DeliverRequest;
import es.codeurjc.deliveryservice.model.events.DeliverResult;
import es.codeurjc.deliveryservice.model.events.dto.OrderDto;
import es.codeurjc.deliveryservice.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
@Transactional
public class DeliveryStreamListener {
	
	private Logger log = LoggerFactory.getLogger(DeliveryStreamListener.class);
	private final DeliveryService deliveryService;
	private final DeliveryStreamService deliveryStreamService;
	
	@Autowired
	public DeliveryStreamListener(DeliveryService deliveryService, DeliveryStreamService deliveryStreamService) {
		this.deliveryService = deliveryService;
		this.deliveryStreamService = deliveryStreamService;
	}
	
	@StreamListener(DeliveryStream.INPUT_DELIVER_ORDER)
	public void handleDeliverRequest(@Payload DeliverRequest deliverRequest) {
		final OrderDto orderDto =  deliverRequest.getOrder();
		final Boolean result = deliveryService.deliverOrder(orderDto);
		final DeliverResult deliverResult = DeliverResult.builder().isValid(result).orderId(orderDto.getId()).reason(Boolean.FALSE.equals(result) ? "NO_CAPACITY":null).build();
		deliveryStreamService.sendDeliverResult(deliverResult);
	}
	
	@StreamListener(DeliveryStream.INPUT_DEDELIVER_ORDER)
    public void handleDedeliverRequest(@Payload DedeliverRequest dedeliverRequest) {
		final OrderDto orderDto =  dedeliverRequest.getOrder();
		deliveryService.dedeliverOrder(orderDto);
	}
}

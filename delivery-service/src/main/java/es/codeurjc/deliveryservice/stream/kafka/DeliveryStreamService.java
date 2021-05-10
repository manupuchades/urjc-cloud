package es.codeurjc.deliveryservice.stream.kafka;

import es.codeurjc.deliveryservice.model.events.DeliverResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class DeliveryStreamService {
	
	private Logger log = LoggerFactory.getLogger(DeliveryStreamService.class);
	
	private final DeliveryStream deliveryStream;
	
	@Autowired
	public DeliveryStreamService(DeliveryStream deliveryStream) {
		this.deliveryStream = deliveryStream;
	}
	
	public void sendDeliverResult(final DeliverResult deliverResult) {
		log.info("Sending deliverResult {}", deliverResult);
		MessageChannel messageChannel = deliveryStream.outboundDeliverOrder();
        messageChannel.send(MessageBuilder
                .withPayload(deliverResult)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
	}
}

package es.codeurjc.deliveryservice.stream.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DeliveryStream {

    String INPUT_DELIVER_ORDER = "deliver-order-request";
    String OUTPUT_DELIVER_ORDER = "deliver-order-response";
    String INPUT_DEDELIVER_ORDER = "dedeliver-order-request";


    @Input(INPUT_DELIVER_ORDER)
    SubscribableChannel inboundDeliverOrder();

    @Output(OUTPUT_DELIVER_ORDER)
    MessageChannel outboundDeliverOrder();

    @Output(INPUT_DEDELIVER_ORDER)
    SubscribableChannel inboundDedeliverOrder();
}

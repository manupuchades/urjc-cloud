package es.codeurjc.deliveryservice.config;

import es.codeurjc.deliveryservice.stream.kafka.DeliveryStream;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(DeliveryStream.class)
public class StreamsConfig {

}

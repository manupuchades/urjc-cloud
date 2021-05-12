package es.codeurjc.orderservice.model.events;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeliverResult {

    private UUID orderId;

    private Boolean isValid;

    private String rejectionReason;

    private UUID deliveryId;

}

package es.codeurjc.deliveryservice.model.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
public class DeliverResult {
	
    private UUID orderId;

    private Boolean isValid;

    private String reason;

}

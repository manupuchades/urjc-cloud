package es.codeurjc.orderservice.dto;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class CreateOrderRequest {

    private BigDecimal orderTotal;
    private UUID customerId;
    private UUID cityId;
    private String productName;
    private String productReference;
    private Integer quantity;

}

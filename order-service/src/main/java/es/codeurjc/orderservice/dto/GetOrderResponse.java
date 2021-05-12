package es.codeurjc.orderservice.dto;


import es.codeurjc.orderservice.types.OrderStatusEnum;
import es.codeurjc.orderservice.types.RejectionReasonEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetOrderResponse {

    private UUID orderId;
    private UUID cityId;
    private UUID deliveryId;
    private OrderStatusEnum orderState;
    private RejectionReasonEnum rejectionReason;

}

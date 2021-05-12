package es.codeurjc.orderservice.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetails {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID customerId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)")
    private UUID deliveryId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)")
    private UUID cityId;

    @Embedded
    private Money orderTotal;

    public void setDeliveryId(UUID deliveryId) {
        this.deliveryId = deliveryId;
    }

}

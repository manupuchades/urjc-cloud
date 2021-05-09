package es.codeurjc.deliveryservice.web;

import es.codeurjc.deliveryservice.dto.DeliveryResponse;
import es.codeurjc.deliveryservice.service.IDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class DeliveryController {
    
    private final IDeliveryService deliveryService;

    @GetMapping("deliveries/{deliveryId}")
    public ResponseEntity<DeliveryResponse> getDelivery(@PathVariable(value = "deliveryId") UUID deliveryId) {
        return new ResponseEntity<>(deliveryService.getDelivery(deliveryId), HttpStatus.OK);
    }
}

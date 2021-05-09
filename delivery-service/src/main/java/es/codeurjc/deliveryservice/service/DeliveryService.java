package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.dto.DeliveryResponse;
import es.codeurjc.deliveryservice.exception.EntityNotFoundException;
import es.codeurjc.deliveryservice.mappers.DeliveryMapper;
import es.codeurjc.deliveryservice.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryService implements IDeliveryService{

    DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResponse getDelivery(UUID deliveryId) {
        return DeliveryMapper.map(deliveryRepository.findById(deliveryId).orElseThrow(() -> new EntityNotFoundException()));
    }
}

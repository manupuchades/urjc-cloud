package es.codeurjc.deliveryservice.model.events.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
public class OrderDto {
	
    @JsonProperty("id")
    private UUID id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("reference")
    private String reference;
    
    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("deliveryId")
    private UUID deliveryId;

    @JsonProperty("cityId")
    private UUID cityId;

    @JsonProperty("route")
    private String route;

    @JsonIgnore
    private String state;

}

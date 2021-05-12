package es.codeurjc.deliveryservice.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "Delivery")
@Access(AccessType.FIELD)
public class Delivery {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    private String route;

    @ManyToOne
    private City city;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    public UUID getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }

    public City getCity() {
        return city;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public static final class Builder {

        private final Delivery object;

        public Builder() {
            object = new Delivery();
        }

        public Builder withId(UUID value) {
            object.id = value;
            return this;
        }

        public Builder withRoute(String route) {
            object.route = route;
            return this;
        }

        public Builder withCity(City city) {
            object.city = city;
            return this;
        }

        public Delivery build() {
            return object;
        }

    }

}

package no.kristiania.orderservice.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private int releasedYear;
    private String color;
    private int price;


    public CarEntity() {
    }


    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", releasedYear=" + releasedYear +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}


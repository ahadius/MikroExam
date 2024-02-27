package no.kristiania.carservice.Cars.repository;

import no.kristiania.carservice.Cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
}

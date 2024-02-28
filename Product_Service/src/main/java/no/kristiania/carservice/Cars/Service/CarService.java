package no.kristiania.carservice.Cars.Service;

import no.kristiania.carservice.Cars.entity.Car;

public interface CarService {
    Car addCar(Car car);
    boolean isCarAvailable(Long carId);
}


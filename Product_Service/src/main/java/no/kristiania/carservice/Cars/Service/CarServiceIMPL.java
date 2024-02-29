package no.kristiania.carservice.Cars.Service;

import lombok.AllArgsConstructor;
import no.kristiania.carservice.Cars.entity.Car;
import no.kristiania.carservice.Cars.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceIMPL implements CarService {

    private CarRepository carRepository;

    @Override
    public Car addCar(Car car) {
        // Save the car entity to the database
        return carRepository.save(car);
    }

    @Override
    public boolean isCarAvailable(Long id) {
        // Check if a car with the given ID exists in the database
        return carRepository.existsById(id);
    }

    @Override
    public Car getCarById(Long id) {
        // Retrieve a car entity from the database by its ID
        return carRepository.findById(id).orElse(null);
    }

}
package no.kristiania.carservice.Cars.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import no.kristiania.carservice.Cars.entity.Car;
import no.kristiania.carservice.Cars.repository.CarRepository;

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
    public boolean isCarAvailable(Long carId) {
        // Implement logic to check if the car with the given ID is available
        return carRepository.existsById(carId);
    }
}




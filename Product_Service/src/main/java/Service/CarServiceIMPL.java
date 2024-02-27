package Service;

import Cars.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CarRepository;

@Service
@AllArgsConstructor
public class  CarServiceIMPL implements CarService {

    private CarRepository carRepository;
@Override
    public Car addCar(Car car) {
        // Save the car entity to the database
        return carRepository.save(car);
    }
}



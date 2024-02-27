package Cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car addCar(Car car) {
        // Save the car entity to the database
        return carRepository.save(car);
    }
}



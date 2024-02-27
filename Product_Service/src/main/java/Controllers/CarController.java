package Controllers;

import Cars.Car;
import Cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    public CarService carService;

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }
}


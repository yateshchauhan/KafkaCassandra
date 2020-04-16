package com.controller;


import com.bean.Car;
import com.bean.Engine;
import com.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    CarService carService;

    @PostMapping("/car/book/{model}")
    public ResponseEntity<String> create(@PathVariable String model, @RequestBody Car car){

        log.info("Car controller service invoked");
        Engine engine = new Engine();
        engine.setModel(model);
        //Engine engine = car.getEngine();
        log.info("Car : {}, engine : {}",car,engine);
        carService.addCar(car,engine);
        return ResponseEntity.status(200).body("Congratulations, your car got booked");
    }
}

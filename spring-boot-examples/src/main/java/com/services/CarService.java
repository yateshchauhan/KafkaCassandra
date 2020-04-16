package com.services;

import com.bean.Car;
import com.bean.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private Logger log = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarDao carDao;

    public CarService(){
        log.info("carService cons invoked");
    }

    public void addCar(Car car, Engine engine){

        log.info("addCar service invoked");
        carDao.bookCar(car,engine);

    }

}

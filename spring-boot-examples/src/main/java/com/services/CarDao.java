package com.services;


import com.bean.Car;
import com.bean.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
public class CarDao {

    private Logger log = LoggerFactory.getLogger(CarDao.class);

    public CarDao(){
        log.info("car doa constructor invoked");
    }

    public void bookCar(Car car, Engine engine){


        log.info("Car inventory added into DB");
    }
}

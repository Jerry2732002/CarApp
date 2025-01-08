package com.example.CarApp.service;

import com.example.CarApp.dao.CarRepo;
import com.example.CarApp.dto.Car;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CarService {
    CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public String addCar(Car car) {
        return carRepo.addCar(car);
    }

    public Car fetchCarById(int id) {
        return carRepo.fetchCarById(id);
    }

    public List<Car> fetchCarByIds(String ids) {
        List<Car> cars = new ArrayList<>();
        Arrays.stream(ids.split(",")).forEach(id -> {
            Car car =carRepo.fetchCarById(Integer.parseInt(id));
            cars.add(car);
        });
        return cars;
    }

    public String removeCar(int id) {
        return carRepo.removeCar(id);
    }

    public String updateCar(Car car, int id) {
        return carRepo.updateCar(car,id);
    }

    public String updateExpiryDate(LocalDate newExpiry, int id) {
        Car original = fetchCarById(id);
        original.setExpiryDate(newExpiry);
        return carRepo.updateCar(original,id);
    }
}

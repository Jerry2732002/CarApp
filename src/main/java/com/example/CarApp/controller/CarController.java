package com.example.CarApp.controller;

import com.example.CarApp.dto.Car;
import com.example.CarApp.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mycarCompany")
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(path = "add")
    public String addCar(@Valid @RequestBody Car car) {
        return carService.addCar(car);
    }

    @GetMapping(path = "id/{id}", produces = "application/json")
    public Car fetchCarById(@PathVariable("id")  @Positive int id) {
        return carService.fetchCarById(id);
    }

    @GetMapping(path = "ids/{ids}", produces = "application/json")
    public List<Car> fetchCarById(@PathVariable("ids") String ids) {
        return carService.fetchCarByIds(ids);
    }

    @DeleteMapping(path = "delete/{id}", produces = "application/json")
    public String removeCar(@PathVariable("id") int id) {
        return carService.removeCar(id);
    }

    @PutMapping(path = "update/{id}", produces = "application/json")
    public String updateCar(@Valid @RequestBody Car car, @PathVariable("id") int id) {
        return carService.updateCar(car,id);
    }

    @PatchMapping(path = "update_expiryDate", produces = "application/json")
    public String updateCar(@RequestBody String newExpiry, @PathVariable("id") int id) {
        LocalDate date = LocalDate.parse(newExpiry);
        return carService.updateExpiryDate(date,id);
    }
}


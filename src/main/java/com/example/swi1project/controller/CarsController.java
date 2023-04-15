package com.example.swi1project.controller;

import com.example.swi1project.model.Account;
import com.example.swi1project.model.Car;
import com.example.swi1project.repository.CarRepository;
import com.example.swi1project.services.AccountService;
import com.example.swi1project.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarsController {
    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars")
    public Car create(@Valid @RequestBody Car newCar) {
        return carService.create(newCar);
    }

    @GetMapping("/cars")
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/cars/brand/{brand}")
    public List<Car> getByBrand(@PathVariable("brand") String brand) {
        return carService.getByBrand(brand);
    }

    @GetMapping("/getCarById/{id}")
    public Car get(@PathVariable("id") long id) {
        return carService.getById(id);
    }

    @PutMapping("/cars/{id}")
    public void update(@PathVariable("id") long id, @Valid @RequestBody Car car) throws Exception {
        car.setId(id);
        carService.update(car);
    }

    @DeleteMapping("/cars/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        carService.delete(id);
    }
}

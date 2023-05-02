package com.example.swi1project.services;

import com.example.swi1project.model.Car;

import java.util.List;

public interface CarService {
    Car create(Car newCar);
    Car getById(long id);
    void update(Car car) throws Exception;
    void delete(long id) throws Exception;
    List<Car> getAll();
    List<Car> getByBrand(String brand);
    List<Car> getCarsNotInOrder();
}

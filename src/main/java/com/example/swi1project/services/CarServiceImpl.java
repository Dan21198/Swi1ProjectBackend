package com.example.swi1project.services;

import com.example.swi1project.exception.RecordNotFoundException;
import com.example.swi1project.model.Car;
import com.example.swi1project.model.Order;
import com.example.swi1project.repository.CarRepository;
import com.example.swi1project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{
    private CarRepository carRepository;
    private OrderRepository orderRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Car create(Car newCar) {
        newCar.setName(newCar.getBrand() + " " + newCar.getModelOfCar());
        Car ret = carRepository.save(newCar);
        return ret;
    }

    @Override
    public Car getById(long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Car not found."));
        return car;
    }

    @Override
    public void update(Car car) throws RecordNotFoundException {
        Optional<Car> optionalExistingCar = carRepository.findById(car.getId());
        if (optionalExistingCar.isEmpty()) {
            throw new RecordNotFoundException("Car not found.");
        }
        Car existingCar = optionalExistingCar.get();
        Order existingOrder = existingCar.getOrder();

        existingCar.setName(car.getBrand() + " " + car.getModelOfCar());
        existingCar.setBrand(car.getBrand());
        existingCar.setYearOfProduction(car.getYearOfProduction());
        existingCar.setModelOfCar(car.getModelOfCar());
        existingCar.setKm(car.getKm());
        existingCar.setPrice(car.getPrice());

        if (car.getOrder() != null) {
            if (existingOrder != null) {
                existingOrder.setId(car.getOrder().getId());
            } else {
                existingOrder = car.getOrder();
                existingCar.setOrder(existingOrder);
            }
        } else {
            existingCar.setOrder(null);
        }

        carRepository.save(existingCar);
    }

    @Override
    public void delete(long id) throws Exception {
        boolean exists = carRepository.existsById(id);
        if(exists){
            carRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("Car not found.");
        }
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public List<Car> getCarsNotInOrder() {
        return carRepository.findByOrderIdIsNull();
    }
}

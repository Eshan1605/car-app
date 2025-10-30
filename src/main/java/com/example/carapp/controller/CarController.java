package com.example.carapp.controller;

import com.example.carapp.model.Car;
import com.example.carapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> getAllCars(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return carRepository.findAll();
        } else {
            return carRepository.findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(search, search);
        }
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }
    
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }
    
 // PUT /cars/{id} -> update existing car
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carRepository.findById(id).map(car -> {
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setPrice(updatedCar.getPrice());
            car.setImageUrl(updatedCar.getImageUrl());
            car.setYear(updatedCar.getYear());
            car.setFuelType(updatedCar.getFuelType());
            car.setMileage(updatedCar.getMileage());
            car.setDescription(updatedCar.getDescription());
            carRepository.save(car);
            return ResponseEntity.ok(car);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

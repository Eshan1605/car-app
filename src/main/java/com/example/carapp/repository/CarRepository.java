package com.example.carapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carapp.model.Car;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    // Search cars where brand or model contains the given keyword (case-insensitive)
    List<Car> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}

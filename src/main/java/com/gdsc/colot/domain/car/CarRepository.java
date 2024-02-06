package com.gdsc.colot.domain.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByPlateNum(String plateNum);
    Optional<Car> findByPlateNum(String plateNum);
}

package com.gdsc.colot.domain.carStopper;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarStopperRepository extends JpaRepository<CarStopper, Long> {
    boolean existsBySerialNum(String serialNum);
    Optional<CarStopper> findBySerialNum(String serialNum);
}

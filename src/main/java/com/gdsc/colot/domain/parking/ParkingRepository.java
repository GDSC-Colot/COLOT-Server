package com.gdsc.colot.domain.parking;

import com.gdsc.colot.domain.car.Car;
import com.gdsc.colot.domain.carStopper.CarStopper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Optional<Parking> findByParkingStatusAndCarAndCarStopper(ParkingStatus parkingStatus, Car car, CarStopper carStopper);
    boolean existsByParkingStatusAndCarStopper(ParkingStatus parkingStatus, CarStopper carStopper);
}

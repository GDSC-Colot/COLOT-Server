package com.gdsc.colot.domain.carStopper;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarStopperRepository extends JpaRepository<CarStopper, Long> {
    boolean existsBySerialNum(String serialNum);
}

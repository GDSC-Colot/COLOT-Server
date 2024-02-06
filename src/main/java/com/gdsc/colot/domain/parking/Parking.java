package com.gdsc.colot.domain.parking;

import com.gdsc.colot.domain.BaseEntity;
import com.gdsc.colot.domain.car.Car;
import com.gdsc.colot.domain.carStopper.CarStopper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "PARKING")
@NoArgsConstructor
@Entity
public class Parking extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ParkingStatus parkingStatus;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "car_stopper_id")
    private CarStopper carStopper;

    @Builder
    public Parking(LocalDateTime startTime, LocalDateTime endTime, ParkingStatus parkingStatus, Car car, CarStopper carStopper) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingStatus = parkingStatus;
        this.car = car;
        this.carStopper = carStopper;
    }

    public void update(LocalDateTime endTime, ParkingStatus parkingStatus) {
        this.endTime = endTime;
        this.parkingStatus = parkingStatus;
    }

}

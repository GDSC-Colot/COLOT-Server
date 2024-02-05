package com.gdsc.colot.domain.carStopper;

import com.gdsc.colot.domain.BaseEntity;
import com.gdsc.colot.domain.parkingLot.ParkingLot;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "CAR_STOPPER")
@Entity
public class CarStopper extends BaseEntity {

    @Column(nullable = false)
    private String serialNum;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @Builder
    public CarStopper(String serialNum, ParkingLot parkingLot) {
        this.serialNum = serialNum;
        this.parkingLot = parkingLot;
    }
}

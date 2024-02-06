package com.gdsc.colot.controller.parkinglot.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingLotResponseDto {

    private Long parkingLotId;
    private Double lat;
    private Double lng;
    private Double distance;
    private Long charge;
    private LocalTime operationStartTime;
    private LocalTime operationEndTime;

    public static ParkingLotResponseDto of(
            Long parkingLotId,
            Double lat,
            Double lng,
            Double distance,
            Long charge,
            LocalTime operationStartTime,
            LocalTime operationEndTime
    ) {
        return new ParkingLotResponseDto(parkingLotId, lat, lng, distance, charge, operationStartTime, operationEndTime);
    }

}

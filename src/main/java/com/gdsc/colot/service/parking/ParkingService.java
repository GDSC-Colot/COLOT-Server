package com.gdsc.colot.service.parking;

import com.gdsc.colot.controller.parking.dto.ParkingRequestDto;
import com.gdsc.colot.domain.car.Car;
import com.gdsc.colot.domain.car.CarRepository;
import com.gdsc.colot.domain.carStopper.CarStopper;
import com.gdsc.colot.domain.carStopper.CarStopperRepository;
import com.gdsc.colot.domain.parking.Parking;
import com.gdsc.colot.domain.parking.ParkingRepository;
import com.gdsc.colot.domain.parking.ParkingStatus;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.model.BadRequestException;
import com.gdsc.colot.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final CarStopperRepository carStopperRepository;
    private final CarRepository carRepository;

    @Transactional
    public boolean parking(ParkingRequestDto requestDto) {
        
        if (requestDto.getStatus().equals("IN")) {
            Car car = carRepository.findByPlateNum(requestDto.getCarPlateNum())
                    .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CAR_EXCEPTION, ErrorCode.NOT_FOUND_CAR_EXCEPTION.getMessage()));

            CarStopper carStopper = carStopperRepository.findBySerialNum(requestDto.getCarStopperId())
                    .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CAR_STOPPER_EXCEPTION, ErrorCode.NOT_FOUND_CAR_STOPPER_EXCEPTION.getMessage()));

            if (parkingRepository.existsByParkingStatusAndCarStopper(ParkingStatus.IN, carStopper)) {
                throw new BadRequestException(ErrorCode.ALREADY_CAR_PARKED_EXCEPTION, ErrorCode.ALREADY_CAR_PARKED_EXCEPTION.getMessage());
            }

            Parking parking = Parking.builder()
                    .startTime(LocalDateTime.now())
                    .parkingStatus(ParkingStatus.IN)
                    .car(car)
                    .carStopper(carStopper)
                    .build();

            parkingRepository.save(parking);
            return true;
        } else if (requestDto.getStatus().equals("OUT")) {
            Car car = carRepository.findByPlateNum(requestDto.getCarPlateNum())
                    .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CAR_EXCEPTION, ErrorCode.NOT_FOUND_CAR_EXCEPTION.getMessage()));

            CarStopper carStopper = carStopperRepository.findBySerialNum(requestDto.getCarStopperId())
                    .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CAR_STOPPER_EXCEPTION, ErrorCode.NOT_FOUND_CAR_STOPPER_EXCEPTION.getMessage()));

            Parking parking = parkingRepository.findByParkingStatusAndCarAndCarStopper(ParkingStatus.IN, car, carStopper)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PARKING_EXCEPTION, ErrorCode.NOT_FOUND_PARKING_EXCEPTION.getMessage()));

            parking.update(LocalDateTime.now(), ParkingStatus.OUT);

            parkingRepository.save(parking);
            return false;
        } else {
            throw new BadRequestException(ErrorCode.PARKING_STATUS_INVALID, ErrorCode.PARKING_STATUS_INVALID.getMessage());
        }

    }

}

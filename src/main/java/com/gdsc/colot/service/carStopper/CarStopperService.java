package com.gdsc.colot.service.carStopper;

import com.gdsc.colot.controller.carStopper.dto.request.CarStopperRequestDto;
import com.gdsc.colot.domain.carStopper.CarStopperRepository;
import com.gdsc.colot.domain.parkingLot.ParkingLot;
import com.gdsc.colot.domain.parkingLot.ParkingLotRepository;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarStopperService {

    private final CarStopperRepository carStopperRepository;
    private final ParkingLotRepository parkingLotRepository;

    public void createCarStopper(CarStopperRequestDto requestDto) {
        ParkingLot parkingLot = parkingLotRepository.findById(requestDto.getParkingLotId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PARKING_LOT_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
    }

}
